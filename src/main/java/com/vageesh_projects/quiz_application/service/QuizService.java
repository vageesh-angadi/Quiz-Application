package com.vageesh_projects.quiz_application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vageesh_projects.quiz_application.QuestionWrapper;
import com.vageesh_projects.quiz_application.Response;
import com.vageesh_projects.quiz_application.dao.QuestionDao;
import com.vageesh_projects.quiz_application.dao.QuizDao;
import com.vageesh_projects.quiz_application.model.Question;
import com.vageesh_projects.quiz_application.model.Quiz;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;
    
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDb = quiz.get().getQuestions();
        ArrayList<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Question q:questionsFromDb){
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }
        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        // Optional<Quiz> quiz = quizDao.findById(id);
        // List<Question> questions = quiz.get().getQuestions();
        Integer result = 0;
        for(Response r:responses){
            Optional<Question> q = questionDao.findById(r.getId());
            if(q.get().getRightAnswer().equals(r.getResponse())){
                result +=1;
            }
        }
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }
}
