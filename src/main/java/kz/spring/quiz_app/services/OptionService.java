package kz.spring.quiz_app.services;

import kz.spring.quiz_app.model.Options;
import kz.spring.quiz_app.repositories.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionService {
    private final OptionRepository optionRepository;

    @Autowired
    public OptionService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    public List<Options> findByQuestionId(Long id){
        return optionRepository.findByQuestionsQuestionId(id);
    }
}
