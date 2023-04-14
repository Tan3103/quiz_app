package kz.spring.quiz_app.services;


import kz.spring.quiz_app.model.Questions;
import kz.spring.quiz_app.model.Quiz;
import kz.spring.quiz_app.repositories.QuestiosnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestiosnRepository questiosnRepository;
    public List<Questions> getQuestionsByQuiz(Quiz quiz) {
        return questiosnRepository.findByQuiz(quiz);
    }
}
