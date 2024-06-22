package com.dsa.binarybeats.Interface;

import java.util.List;

import com.dsa.binarybeats.Entity.Quiz;
import com.dsa.binarybeats.Request.QuizRequest;

public interface IQuizService {

    public boolean addQuiz(List<QuizRequest> quizRequest);

    List<Quiz> getAllQuiz(List<String> topic);
    
}
