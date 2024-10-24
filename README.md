# Quiz Application

This is a backend service for a Quiz Application developed using Spring Boot, where users can create quizzes, add questions, and submit answers. The application allows for category-based question retrieval, random question selection, and result calculation based on user responses.

## Table of Contents
- [Overview](#overview)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [API Endpoints](#api-endpoints)
- [Installation](#installation)
- [Usage](#usage)

## Overview
The Quiz Application is a backend system designed for:
1. Creating quizzes with questions pulled randomly from a specified category.
2. Retrieving quizzes by ID for users to attempt.
3. Submitting quiz answers and calculating the result based on correct responses.
### Key Features:
- REST APIs for creating quizzes, adding questions, and retrieving quiz data.
- Category-based question selection.
- Randomized question selection to ensure variability in quizzes.
- Result calculation based on correct answers provided by the user.
## Technologies Used
- **Java**
- **Spring Boot** - for creating REST APIs and handling backend services.
- **Spring Data JPA** - for database interactions.
- **PostgreSQL** (or any other SQL DB) - for persisting quiz data.
- **Lombok** - to reduce boilerplate code for model classes.

## Project Structure
src
│── main
│   └── java
│       └── com.vageesh_projects.quiz_application
│           ├── controller
│           ├── dao
│           ├── model
│           ├── service
│           ├── QuizApplication.java
│
└── resources
    ├── application.properties
- **Controller**: Handles HTTP requests and responses.
- **Service**: Contains business logic for managing questions and quizzes.
- **DAO**: Contains repository interfaces for database interactions using JPA.
- **Model**: Contains entities such as `Question` and `Quiz` for database mapping.

## API Endpoints
### Question APIs
- **GET /question/allquestions**
  - Retrieves all questions in the database.
- **GET /question/category/{category}**
  - Retrieves all questions based on the specified category.
- **POST /question/addquestion**
  - Adds a new question to the database.

### Quiz APIs
- **POST /quiz/create**
  - Creates a quiz with a specified category, number of questions, and title.
  - Parameters:
    - `category`: Category of questions.
    - `numQ`: Number of questions to include.
    - `title`: Title of the quiz.
- **GET /quiz/get/{id}**
  - Retrieves questions for a quiz by its ID.
- **POST /quiz/submit/{id}**
  - Submits answers for a quiz and calculates the score.
  - Payload: List of user responses (`id` and `response` for each question).

## Installation
1. **Clone the repository:**
   git clone https://github.com/vageesh-angadi/quiz-application.git
   cd quiz-application
2. **Configure the database:**
   In `src/main/resources/application.properties`, set up your database connection properties:
   spring.datasource.url=jdbc:postgresql://localhost:5432/quiz_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
3. **Build and run the project:**
   ./mvnw spring-boot:run

## Usage
Once the application is running, you can interact with it using an API client (e.g., Postman) or via cURL commands.
### Example: Add a Question
POST /question/addquestion
{
  "questionTitle": "What is the capital of France?",
  "option1": "Berlin",
  "option2": "Madrid",
  "option3": "Paris",
  "option4": "Rome",
  "rightAnswer": "Paris",
  "difficultyLevel": "easy",
  "category": "geography"
}

### Example: Create a Quiz
POST /quiz/create?category=geography&numQ=5&title=Geography Quiz
### Example: Submit Quiz Answers
POST /quiz/submit/1
[
  {
    "id": 1,
    "response": "Paris"
  },
  {
    "id": 2,
    "response": "Rome"
  }
]

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
