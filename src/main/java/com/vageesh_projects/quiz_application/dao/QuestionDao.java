package com.vageesh_projects.quiz_application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vageesh_projects.quiz_application.model.Question;
@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {

    List<Question> findAllByCategory(String category);
    
    @Query(value = "SELECT * FROM question WHERE category=:category ORDER BY RANDOM() LIMIT :numQ",nativeQuery = true)
    List<Question> findRandomQuestionByCategory(String category, int numQ);
}
