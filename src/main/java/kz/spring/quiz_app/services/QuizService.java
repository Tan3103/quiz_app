package kz.spring.quiz_app.services;

import kz.spring.quiz_app.model.Quiz;
import kz.spring.quiz_app.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuizService {
    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Quiz getQuizById(Long id){
        Optional<Quiz> quiz = quizRepository.findById(id);
        return quiz.orElse(null);
    }
}
