package org.example.ibmskillsbuildapp.controller;

import java.util.List;
import org.example.ibmskillsbuildapp.model.QuestionForm;
import org.example.ibmskillsbuildapp.model.Result;
import org.example.ibmskillsbuildapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/quiz")
    public String quiz(Model model) {
        QuestionForm questionForm = quizService.getQuestions();
        model.addAttribute("qForm", questionForm);
        model.addAttribute("result", new Result()); // Initializing Result for the form
        return "quiz";
    }

    @PostMapping("/submitQuiz")
    public String submitQuiz(@ModelAttribute QuestionForm questionForm, Model model) {
        Result result = new Result();
        int totalCorrect = quizService.getResult(questionForm);
        result.setTotalCorrect(totalCorrect);
        quizService.saveScore(result);
        model.addAttribute("result", result);
        return "result";
    }

    @GetMapping("/scoreboard")
    public String scoreboard(Model model) {
        List<Result> scoreList = quizService.getTopScores();
        model.addAttribute("scoreList", scoreList);
        return "scoreboard";
    }
}
