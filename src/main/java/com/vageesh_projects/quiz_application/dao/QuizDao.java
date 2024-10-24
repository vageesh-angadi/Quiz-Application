package com.vageesh_projects.quiz_application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vageesh_projects.quiz_application.model.Question;
import com.vageesh_projects.quiz_application.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz,Integer>{

}
