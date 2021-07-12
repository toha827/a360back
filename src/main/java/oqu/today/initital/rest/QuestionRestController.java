package oqu.today.initital.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import oqu.today.initital.model.Course;
import oqu.today.initital.model.Option;
import oqu.today.initital.model.Question;
import oqu.today.initital.model.Quiz;
import oqu.today.initital.model.request.OptionDto;
import oqu.today.initital.model.request.QuestionDTO;
import oqu.today.initital.repository.QuestionOptionRepostory;
import oqu.today.initital.repository.QuestionRepository;
import oqu.today.initital.repository.QuizRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/javaApi/api/question")
public class QuestionRestController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionOptionRepostory questionOptionRepostory;

    @Autowired
    private QuizRepository quizRepository;

    @PostMapping
    public ResponseEntity craete(@RequestBody QuestionDTO question) {
        try {
            Optional<Quiz> _quiz = quizRepository.findById(question.getQuiz().getId());
            if (_quiz.isPresent()) {
                return new ResponseEntity(questionRepository.save(question.toEntity()), HttpStatus.OK);
            } else {

                return new ResponseEntity("QUIZ NOT FOUND",HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable long id){
        Optional<Question> _question = questionRepository.findById(id);

        if(_question.isPresent()) {
            return new ResponseEntity(_question.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity getAll() {
        try {
            return new ResponseEntity(questionRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/option")
    public ResponseEntity createOption(@RequestBody OptionDto option) {
        try {
            Optional<Question> _question = questionRepository.findById(option.getQuestion().getId());

            if(_question.isPresent()) {
                return new ResponseEntity(questionOptionRepostory.save(option.toEntity()), HttpStatus.OK);
            } else {
                return  new ResponseEntity("Question not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
