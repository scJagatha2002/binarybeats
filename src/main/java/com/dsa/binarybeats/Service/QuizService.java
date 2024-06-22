package com.dsa.binarybeats.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsa.binarybeats.Entity.Option;
import com.dsa.binarybeats.Entity.Quiz;
import com.dsa.binarybeats.Interface.IQuizService;
import com.dsa.binarybeats.Repository.OptionRepo;
import com.dsa.binarybeats.Repository.QuestionRepo;
import com.dsa.binarybeats.Request.OptionRequest;
import com.dsa.binarybeats.Request.QuizRequest;



@Service
public class QuizService implements IQuizService {

    @Autowired
    private OptionRepo optionRepo;

    @Autowired
    private QuestionRepo questionRepo;

    @Override
    public boolean addQuiz(List<QuizRequest> quizRequest) {
        List<Quiz> questions = new ArrayList<>();
        for(QuizRequest q:quizRequest){
            Quiz quiz = new Quiz();

            List<Option> options = new ArrayList<>();
            for(OptionRequest or : q.getOptions()){
                Option option = new Option();
                option.setValue(or.getValue());
                option.setIsCorrect(or.getIsCorrect());
                options.add(option);
            }
            optionRepo.saveAll(options);



            quiz.setOptions(options);
            quiz.setQuestion(q.getQuestion());
            quiz.setTopic(q.getTopic());

            questions.add(quiz);
        }
        questionRepo.saveAll(questions);
        return true;
    }

    @Override
    public List<Quiz> getAllQuiz(List<String> topic) {
        
        if(topic.isEmpty()){
            return questionRepo.findAll();
        }
        
        return questionRepo.getQuiz(topic);
    }
    
}
