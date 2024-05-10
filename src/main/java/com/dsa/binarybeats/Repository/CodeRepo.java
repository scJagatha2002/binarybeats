package com.dsa.binarybeats.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dsa.binarybeats.Entity.Code;

import java.util.List;
import java.util.Optional;

public interface CodeRepo extends JpaRepository<Code, Long> {
    Optional<Code> findById(long id);

     @Query("SELECT c FROM Code c " +
                        "WHERE (c.topic = :Topic OR :Topic = '') " +
                        "AND (c.Difficulty.difficulty_name = :Difficulty OR :Difficulty= '')" + "ORDER BY " +
                        "CASE WHEN :sort = 'Easy To Difficult' THEN c.Difficulty.id END ASC, " +
                        "CASE WHEN :sort = 'Difficult To Easy' THEN c.Difficulty.id END DESC")
    List<Code> filterAndSort(@Param("Topic") String topic,
            @Param("Difficulty") String difficulty,
            @Param("sort") String Sort);
}
