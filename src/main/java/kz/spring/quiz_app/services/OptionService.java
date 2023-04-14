package kz.spring.quiz_app.services;

import kz.spring.quiz_app.model.Options;
import kz.spring.quiz_app.repositories.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionService {

    @Autowired
    private OptionRepository optionRepository;
    public List<Options> findById(Long id){
        return optionRepository.findByQuestions(id);
    }
}
