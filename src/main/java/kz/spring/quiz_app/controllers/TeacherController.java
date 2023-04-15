package kz.spring.quiz_app.controllers;

import jakarta.validation.Valid;
import kz.spring.quiz_app.model.Options;
import kz.spring.quiz_app.model.Questions;
import kz.spring.quiz_app.services.OptionService;
import kz.spring.quiz_app.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    private final QuestionService questionService;
    private final OptionService optionService;;

    @Autowired
    public TeacherController(QuestionService questionService, OptionService optionService) {
        this.questionService = questionService;
        this.optionService = optionService;
    }

    @GetMapping("/update/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("question", questionService.findOne(id));
        model.addAttribute("options", optionService.findByQuestionId(id));

        return "teacher/update";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        questionService.delete(id);
        return "redirect:/";
    }

    @PatchMapping("/update/{id}")
    public String update(@ModelAttribute("question") @Valid Questions question, BindingResult bindingResult,
                         @ModelAttribute("options") @Valid Options options,
                         @PathVariable("id") Long id) {

//        if (bindingResult.hasErrors())
//            return "people/edit";

        questionService.update(id, question);
        return "redirect:/";
    }
}
