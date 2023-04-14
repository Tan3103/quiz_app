package kz.spring.quiz_app.controllers;
import kz.spring.quiz_app.model.Questions;
import kz.spring.quiz_app.model.Quiz;
import kz.spring.quiz_app.services.OptionService;
import kz.spring.quiz_app.services.QuestionService;
import kz.spring.quiz_app.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class QuizController {

    private final QuestionService questionService;
    private final OptionService optionService;
    private final QuizService quizService;

    @Autowired
    public QuizController(QuestionService questionService, OptionService optionService, QuizService quizService) {
        this.questionService = questionService;
        this.optionService = optionService;
        this.quizService = quizService;
    }

    @GetMapping(value = "/questions/{idshka}")
    public String quiz(Model model, @PathVariable(name = "idshka") Long id){

        System.out.println(id);
        Quiz quiz = quizService.getQuizById(id);
        System.out.println(quiz);
        model.addAttribute("quiz", quiz);

//        List<Questions> questionsList = questionService.getQuestionsByQuiz(quizList);
//        List<Options> optionsList = optionService.findById(optId);
//        model.addAttribute("voprosy", questionsList);
//        model.addAttribute("otvety", optionsList);

        return "questions";
    }


//    @PostMapping(value = "/questions/{idshka}")
//    public String postquiz(@RequestParam(name = "item_name", defaultValue = "No Item") String name){
//
//
//
//        return "";
//    }
}
