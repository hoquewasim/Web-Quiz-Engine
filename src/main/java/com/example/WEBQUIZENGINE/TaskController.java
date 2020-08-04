package com.example.WEBQUIZENGINE;

import com.example.WEBQUIZENGINE.AnswerResponse;
import com.example.WEBQUIZENGINE.Quiz;
import com.example.WEBQUIZENGINE.QuizRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController("test")
public class TaskController {
    QuizRepository quizRepository = new QuizRepository();

    public TaskController() {
    }

    @GetMapping(value = "/api/quizzes/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Quiz getQuiz(@PathVariable("id") int id) throws ResponseStatusException{
        Quiz quiz =  quizRepository.getQuizById(id);
        if (quiz != null) {
            return quiz;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such quiz");
    }

    @GetMapping(value = "/api/quizzes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Quiz> getAllQuizzes() {
        List<Quiz> quizzes = quizRepository.getQuizzes() != null ?
                quizRepository.getQuizzes() : new ArrayList<>();
        return quizRepository.getQuizzes();
    }

    @PostMapping(value = "/api/quizzes",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = "application/json")
    public Quiz createQuiz(@RequestBody Quiz quiz) {
        quizRepository.addQuiz(quiz);
        return quiz;
    }

    @PostMapping(value = "/api/quizzes/{id}/solve",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = "application/x-www-form-urlencoded")
    public AnswerResponse answerQuiz(@PathVariable("id") int id, @RequestParam("answer") int answer) {
        return quizRepository.getQuizById(id).answerResponse(answer);
    }
}