package kz.spring.quiz_app.services;

import kz.spring.quiz_app.model.Questions;
import kz.spring.quiz_app.model.User;
import kz.spring.quiz_app.repositories.QuestiosnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestiosnRepository questiosnRepository;

    @Autowired
    public QuestionService(QuestiosnRepository questiosnRepository) {
        this.questiosnRepository = questiosnRepository;
    }

    public List<Questions> getAllQuestions(){
        return questiosnRepository.findAll();
    }

    public Questions findOne(Long id){
        Optional<Questions> foundQuestion = questiosnRepository.findById(id);

        return foundQuestion.orElse(null);
    }

    public Questions addQuestion(Questions question){
        return questiosnRepository.save(question);
    }

    public void delete(Long id){
        questiosnRepository.deleteById(id);
    }

    public void update(Long id, Questions question){
        question.setQuestionId(id);
        questiosnRepository.save(question);
    }
}
