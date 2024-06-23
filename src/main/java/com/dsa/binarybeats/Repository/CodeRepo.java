package com.dsa.binarybeats.Repository;

import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dsa.binarybeats.Entity.Code;

import java.util.List;
import java.util.Optional;

public interface CodeRepo extends JpaRepository<Code, Long> {
        Optional<Code> findById(long id);

        @Query("SELECT c FROM Code c " +
                        "WHERE c.topic IN :topic " +
                        "AND c.Difficulty.difficulty_name IN :difficulty " +
                        "ORDER BY " +
                        "CASE WHEN :sort = 'Easy To Difficult' THEN c.Difficulty.id END ASC, " +
                        "CASE WHEN :sort = 'Difficult To Easy' THEN c.Difficulty.id END DESC")
        List<Code> filterAndSort(@Param("topic") List<String> topic,
                        @Param("difficulty") List<String> difficulty,
                        @Param("sort") String sort);

        @Query("SELECT c FROM Code c " +
                        "WHERE c.topic IN :topic " +
                        "ORDER BY " +
                        "CASE WHEN :sort = 'Easy To Difficult' THEN c.Difficulty.id END ASC, " +
                        "CASE WHEN :sort = 'Difficult To Easy' THEN c.Difficulty.id END DESC")
        List<Code> filterOnTopicAndSort(@Param("topic") List<String> topic,
                        @Param("sort") String sort);

        @Query("SELECT c FROM Code c " +
                        "WHERE c.Difficulty.difficulty_name IN :difficulty " +
                        "ORDER BY " +
                        "CASE WHEN :sort = 'Easy To Difficult' THEN c.Difficulty.id END ASC, " +
                        "CASE WHEN :sort = 'Difficult To Easy' THEN c.Difficulty.id END DESC")
        List<Code> filterOnDifficultyAndSort(@Param("difficulty") List<String> difficulty,
                        @Param("sort") String sort);

        @Query("SELECT c FROM Code c " +
                        "ORDER BY " +
                        "CASE WHEN :sort = 'Easy To Difficult' THEN c. Difficulty.id END ASC, " +
                        "CASE WHEN :sort = 'Difficult To Easy' THEN c. Difficulty.id END DESC")
        List<Code> findAll(@Param("sort") String sort);

}
