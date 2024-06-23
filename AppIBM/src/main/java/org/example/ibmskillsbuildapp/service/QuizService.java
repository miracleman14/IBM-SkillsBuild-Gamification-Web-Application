package org.example.ibmskillsbuildapp.service;

import java.util.ArrayList;
import java.util.List;
import org.example.ibmskillsbuildapp.model.Question;
import org.example.ibmskillsbuildapp.model.QuestionForm;
import org.example.ibmskillsbuildapp.model.Result;
import org.example.ibmskillsbuildapp.repo.ResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    private static final List<Question> hardcodedQuestions = createHardcodedQuestions();
    private ResultRepo resultRepo;

    @Autowired
    public QuizService(ResultRepo resultRepo) {
        this.resultRepo = resultRepo;
    }

    private static List<Question> createHardcodedQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(
            new Question(1, "Which framework is used for building enterprise applications in Java?",
                "Spring Framework", "ReactJS", "Vue.js", 1)); // Option A is correct
        questions.add(new Question(2,
            "IBM Watson SDK for Java is used to integrate with what type of IBM services",
            "1989", "Artificial intelligence services", "2000", 2)); // Option B is correct
        questions.add(
            new Question(3, "What is the principle of writing reusable code components in Java?",
                "Cloud storage", "Artificial intelligence services", "Blockchain services",
                3)); // Option C is correct
        questions.add(new Question(4,
            "Which tool is preferred for continuous integration/continuous delivery (CI/CD) in Java projects, often used in IBM tutorials?",
            "Jenkins", "Travis CI", "GitLab CI", 1)); // Option A is correct
        return questions;
    }

    public QuestionForm getQuestions() {
        QuestionForm questionForm = new QuestionForm();
        questionForm.setQuestions(new ArrayList<>(hardcodedQuestions));
        return questionForm;
    }

    public int getResult(QuestionForm questionForm) {
        int correctAnswers = 0;
        for (Question question : questionForm.getQuestions()) {

        }
        return correctAnswers;
    }

    public void saveScore(Result result) {
        resultRepo.save(result);
    }

    public List<Result> getTopScores() {
        return resultRepo.findAll(Sort.by(Sort.Direction.DESC, "totalCorrect"));
    }
}
