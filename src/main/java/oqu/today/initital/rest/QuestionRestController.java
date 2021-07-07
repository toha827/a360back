package oqu.today.initital.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import oqu.today.initital.model.Course;
import oqu.today.initital.model.Question;
import oqu.today.initital.repository.QuestionRepository;
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

    @GetMapping(value = "/byCourse", produces = "application/json")
    public ResponseEntity<List<Question>> getByCourse(@RequestParam long id) {
        try {
            Optional<List<Question>> _list = questionRepository.findAllByCourseCourseId(id);
            if (_list.isPresent()) {
                return new ResponseEntity(_list.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
