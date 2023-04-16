package kz.spring.quiz_app.controllers;

import jakarta.validation.Valid;
import kz.spring.quiz_app.model.Options;
import kz.spring.quiz_app.model.Questions;
import kz.spring.quiz_app.model.Quiz;
import kz.spring.quiz_app.services.OptionService;
import kz.spring.quiz_app.services.QuestionService;
import kz.spring.quiz_app.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    private final QuestionService questionService;
    private final OptionService optionService;;

    @Autowired
    private QuizService quizService;

    @Autowired
    public TeacherController(QuestionService questionService, OptionService optionService) {
        this.questionService = questionService;
        this.optionService = optionService;
    }

    @GetMapping("/update/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("question", questionService.findOne(id));

        return "teacher/update";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        questionService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/addquiz")
    public String addGet(@ModelAttribute("quiz") Quiz quiz){
        System.out.println("Work");
        return "teacher/add";
    }

    @PostMapping("/addquiz")
    public String add(
            @RequestParam(name = "name_subject") String quiz_subject,
            @RequestParam(name = "name_title") String title,
            @RequestParam(name = "name_options1") String option1,
            @RequestParam(name = "name_options2") String option2,
            @RequestParam(name = "name_options3") String option3,
            @RequestParam(name = "name_options4") String option4,
            @RequestParam(name = "correct_options") int correctOption
                      ) {
        List<Quiz> quizList = quizService.getAllQuiz();
        for(Quiz quiz: quizList){
            if (quiz.getSubject().equals(quiz_subject)){
                Quiz quizById = quizService.getQuizById(quiz.getId());
                Questions question = new Questions( title,-1, correctOption, quizById);
                questionService.addQuestion(question);
                Options options1 = new Options( option1, question);
                Options options2 = new Options( option2, question);
                Options options3 = new Options( option3, question);
                Options options4 = new Options( option4, question);
                optionService.addOption(options1);
                optionService.addOption(options2);
                optionService.addOption(options3);
                optionService.addOption(options4);
                break;
            }
            else{

                Quiz quiz1 = new Quiz(null,quiz_subject);
                System.out.println(quiz1);
                quizService.addQuiz(quiz1);
                Questions question = new Questions(title,-1, correctOption, quiz1);
                questionService.addQuestion(question);
                Options options1 = new Options( option1, question);
                Options options2 = new Options( option2, question);
                Options options3 = new Options( option3, question);
                Options options4 = new Options( option4, question);
                optionService.addOption(options1);
                optionService.addOption(options2);
                optionService.addOption(options3);
                optionService.addOption(options4);
                break;
            }
        }
        return "redirect:/";
    }

    @PatchMapping("/update/{id}")
    public String update(@ModelAttribute("question") @Valid Questions question, BindingResult bindingResult,
                         @PathVariable("id") Long id) {

//        if (bindingResult.hasErrors())
//            return "people/edit";

        for (Options option: question.getOptions()) {
            optionService.update(option.getOptionId(), option);
        }

        questionService.update(id, question);
        return "redirect:/";
    }
}
