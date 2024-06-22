package com.dsa.binarybeats.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dsa.binarybeats.Entity.Quiz;
import com.dsa.binarybeats.Exceptions.BookException;
import com.dsa.binarybeats.Interface.IQuizService;
import com.dsa.binarybeats.Request.QuizRequest;


import java.util.Collections;

import java.util.Arrays;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private IQuizService quizService;

    @PostMapping("/add")
    public ResponseEntity<String> addBook(@RequestBody List<QuizRequest> quizRequest) throws BookException{
        boolean added = quizService.addQuiz(quizRequest);
        if(added){
            return ResponseEntity.ok("Added successfully");
        }
        else{
            throw new BookException("Error adding a new book");
        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<Quiz>>getQuiz(@RequestParam String topic) throws BookException {
        List<String> topics;
        
        if (topic.isEmpty()) {
            topics = Collections.emptyList(); // Empty list if topic parameter is empty
        } else {
            topics = Arrays.asList(topic.split(",")); // Split comma-separated string into list
        }
        
        List<Quiz> quizes = quizService.getAllQuiz(topics);
        return new ResponseEntity<>(quizes, HttpStatus.OK);
    }
    
}
