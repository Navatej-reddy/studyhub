# Studyhub - Spring Boot Scaffold

This is a minimal scaffold of the Studyhub quiz application based on the schema you provided.

What is included:
- Entities for School, Student, Subject, Topic, Subtopic, Question, QuizSession, QuizAttemptDetail, StudentActivity, Leaderboard
- Repositories for each entity
- Basic service and controller examples for Student and Quiz (start/end)
- H2 in-memory DB configured for quick testing

Build & Run:
- mvn clean package
- mvn spring-boot:run
- H2 console: http://localhost:8080/h2-console (jdbc url: jdbc:h2:mem:studyhubdb)

This scaffold is intentionally minimal — intended as a starting point. You should add security (JWT/Bcrypt), validation, DTOs/mappers, tests, and further controllers/services as needed.
