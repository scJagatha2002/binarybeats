package com.dsa.binarybeats.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dsa.binarybeats.Entity.Quiz;

public interface QuestionRepo extends JpaRepository<Quiz, Long> {

    @Query("SELECT c FROM Quiz c WHERE c.topic IN :topics")
    List<Quiz> getQuiz(@Param("topics") List<String> topics);


}
