package com.example.studyhub.service.impl;

import com.example.studyhub.dto.*;
import com.example.studyhub.entity.*;
import com.example.studyhub.repository.*;
import com.example.studyhub.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {


    private final QuestionRepository questionRepo;
    private final QuizSessionRepository quizSessionRepo;
    private final QuizAttemptDetailRepository attemptRepo;
    private final StudentRepository studentRepo;
    private final StudentActivityRepository activityRepo;
    private final LeaderboardRepository leaderboardRepo;
    private final SubjectRepository subjectRepository;
    private final TopicRepository topicRepository;

    @Autowired
    public QuizServiceImpl(QuestionRepository questionRepo,
                       QuizSessionRepository quizSessionRepo,
                       QuizAttemptDetailRepository attemptRepo,
                       StudentRepository studentRepo,
                       StudentActivityRepository activityRepo,
                       LeaderboardRepository leaderboardRepo,
                           SubjectRepository subjectRepository,
                           TopicRepository topicRepository
                           ) {
        this.questionRepo = questionRepo;
        this.quizSessionRepo = quizSessionRepo;
        this.attemptRepo = attemptRepo;
        this.studentRepo = studentRepo;
        this.activityRepo = activityRepo;
        this.leaderboardRepo = leaderboardRepo;
        this.subjectRepository = subjectRepository;
        this.topicRepository = topicRepository;
    }

    // Start quiz: choose N random questions and persist QuizSession + QuizAttemptDetail
    @Transactional
    public QuizSessionDTOResponse startQuiz(QuizSessionDTORequest request) {

        Student student = studentRepo.findById(request.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        // Create session

        Subject subject = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        Topic topic = topicRepository.findById(request.getTopicId())
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        QuizSession quizSession = QuizSession.builder()
                .student(student)
                .subject(subject)
                .topic(topic)
                .difficulty(Difficulty.valueOf(request.getDifficulty().toUpperCase()))
                .score(request.getScore())
                .startTime(LocalDateTime.now())
                .build();
        quizSession = quizSessionRepo.save(quizSession);

        // Fetch candidate question IDs efficiently
        List<Long> candidateIds = questionRepo.findIdsByFiltersNative(
                request.getTopicId(), request.getDifficulty().toUpperCase(), student.getClassGrade());

        if (candidateIds.isEmpty())
            throw new IllegalStateException("No questions available for selected filters");

        // Shuffle and pick top N
        Collections.shuffle(candidateIds);
        List<Long> chosen = candidateIds.stream()
                .limit(request.getNumQuestions())
                .collect(Collectors.toList());

        // Persist attempt rows for this session (studentAnswer / isCorrect null now)
        List<QuizAttemptDetail> attempts = new ArrayList<>();
        for (Long qid : chosen) {
            QuizAttemptDetail ad = new QuizAttemptDetail();
            ad.setQuizSession(quizSession);
            //getting the entire question object before saving it into db
            Question q = questionRepo.findById(qid).orElseThrow(() -> new RuntimeException("student not found"));
            ad.setQuestion(q);
            ad.setIsCorrect(null);
            attempts.add(ad);
        }
        attemptRepo.saveAll(attempts);

        // Load questions for sending to frontend but hide correctOption
        List<QuizQuestionAttemptDTO> questionAttemptDTOs = attempts.stream()
                .map(attempt -> {
                    Question q = attempt.getQuestion();
                    QuizQuestionAttemptDTO dto = new QuizQuestionAttemptDTO();
                    dto.setAttemptId(attempt.getAttemptId());
                    dto.setQuestionId(q.getQuestionId());
                    dto.setQuestionText(q.getQuestionText());
                    dto.setOptionA(q.getOptionA());
                    dto.setOptionB(q.getOptionB());
                    dto.setOptionC(q.getOptionC());
                    dto.setOptionD(q.getOptionD());
                    return dto;
                })
                .collect(Collectors.toList());

        return QuizSessionDTOResponse.from(quizSession, questionAttemptDTOs);
    }

    // Record answer for a single attempt (could be called per-question)
    @Transactional
    public void submitAnswer(Long attemptId, String answer) {
        QuizAttemptDetail attempt = attemptRepo.findById(attemptId)
                .orElseThrow(() -> new IllegalArgumentException("Attempt not found"));
        Question q = questionRepo.findById(attempt.getQuestion().getQuestionId())
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));
        attempt.setStudentAnswer(answer);
        attempt.setIsCorrect(
                answer != null &&
                        q.getCorrectOption() != null &&
                        answer.trim().equalsIgnoreCase(q.getCorrectOption().trim())
        );
        attemptRepo.save(attempt);
    }


    // End quiz: compute score, update session, leaderboard, student activity
    @Transactional
    public QuizResultDTO endQuiz(Long quizId) {
        QuizSession session = quizSessionRepo.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException("QuizSession not found"));

        List<QuizAttemptDetail> attempts = attemptRepo.findByQuizSession_QuizId(quizId);

        // compute score: count isCorrect = true
        int score = (int) attempts.stream()
                .filter(a -> Boolean.TRUE.equals(a.getIsCorrect()))
                .count();

        session.setScore(score);
        session.setEndTime(LocalDateTime.now());
        quizSessionRepo.save(session);

        // Update StudentActivity
        StudentActivity activity = activityRepo.findByStudent_StudentId(session.getStudent().getStudentId())
                .orElseGet(() -> {
                    StudentActivity sa = new StudentActivity();
                    sa.setStudent(session.getStudent());
                    return sa;
                });
        activity.setHighScore(Math.max(activity.getHighScore() == null ? 0 : activity.getHighScore(), score));
        activityRepo.save(activity);

        // Update Leaderboard: add to totalScore or insert
        Student student = studentRepo.findById(session.getStudent().getStudentId()).get();
        Long schoolId = student.getSchool().getSchoolId();
        Integer classGrade = student.getClassGrade();

        Leaderboard lb = leaderboardRepo.findByStudent_StudentIdAndClassGradeAndSchool_SchoolId(
                        session.getStudent().getStudentId(), classGrade, schoolId)
                .orElseGet(() -> {
                    Leaderboard newLb = new Leaderboard();
                    newLb.getStudent().setStudentId(session.getStudent().getStudentId());
                    newLb.setClassGrade(classGrade);
                    newLb.getSchool().setSchoolId(schoolId);
                    newLb.setTotalScore(0);
                    return newLb;
                });

        lb.setTotalScore((lb.getTotalScore() == null ? 0 : lb.getTotalScore()) + score);
        //lb.setLastUpdated(new Timestamp(System.currentTimeMillis()));
        leaderboardRepo.save(lb);

        return QuizResultDTO.from(session.getQuizId(), score, attempts.size());
    }
}
