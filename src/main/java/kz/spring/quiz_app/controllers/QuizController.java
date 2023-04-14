package kz.spring.quiz_app.controllers;
import kz.spring.quiz_app.model.Quiz;
import kz.spring.quiz_app.services.OptionService;
import kz.spring.quiz_app.services.QuestionService;
import kz.spring.quiz_app.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        Quiz quiz = quizService.getQuizById(id);
        model.addAttribute("quiz", quiz);

        return "student/questions";
    }

    @PostMapping(value = "/submit")
    public String postQuiz(@ModelAttribute Quiz quiz){

        System.out.println(quiz);

        return "redirect:/";
    }
}
