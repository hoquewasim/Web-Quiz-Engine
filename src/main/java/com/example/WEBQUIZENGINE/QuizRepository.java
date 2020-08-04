package com.example.WEBQUIZENGINE;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.example.WEBQUIZENGINE.Quiz;
import java.util.ArrayList;
import java.util.List;

public class QuizRepository {
    private static List<Quiz> quizzes = new ArrayList<Quiz>();
    public static int CURRENT_ID = 1;

    public QuizRepository() {
    }

    public void addQuiz(String title, String text, List<String> options, int answer) {
        quizzes.add(new Quiz(title, text, options, answer));
        CURRENT_ID++;
    }

    public void addQuiz(Quiz quiz) {
        quizzes.add(quiz);
        CURRENT_ID++;
    }

    public Quiz getQuizById(int id) throws ResponseStatusException {
        // if quiz not found in list, please throw a 404.
        ResponseStatusException responseStatusException = new ResponseStatusException(HttpStatus.NOT_FOUND,
                "No such quiz");
        return quizzes.stream().filter(q -> q.getId() == id).findFirst().orElseThrow(() -> responseStatusException);
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }
}