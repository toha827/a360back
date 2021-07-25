package oqu.today.initital.rest;

import oqu.today.initital.model.*;
import oqu.today.initital.model.request.OptionDto;
import oqu.today.initital.model.request.QuizSubmitRequest;
import oqu.today.initital.model.response.QuizResultResponse;
import oqu.today.initital.model.response.UserUpdateResponse;
import oqu.today.initital.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/javaApi/api/quiz")
public class QuizRestController {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionOptionRepostory questionOptionRepostory;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ChapterRepository chapterRepository;

    final String localhost = "http://45.80.70.68";
//    final String localhost = "http://localhost:3000";

    private static final Logger logger = LoggerFactory.getLogger(FileRestContoller.class);

    @PostMapping(value = "")
    public ResponseEntity create(@RequestBody Quiz quiz) {
        try {
            logger.info(quiz.toString());
            Optional<Course> _course = courseRepository.findById(quiz.getCourse().getId());
            Optional<Chapter> _chapter = chapterRepository.findById(quiz.getChapter().getId());

            if (_course.isPresent()) {
                if(_chapter.isPresent()) {
                    quizRepository.save(quiz);
                    return new ResponseEntity(HttpStatus.OK);
                } else {
                    return new ResponseEntity(new UserUpdateResponse("Chapter not found"), HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity(new UserUpdateResponse("Course not found"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity getAll() {
        try {
            return new ResponseEntity(quizRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/byCourse", produces = "application/json")
    public ResponseEntity getByCourse(@RequestParam long id) {
        try {
            Optional<List<Quiz>> _list = quizRepository.findAllByCourseId(id);
            if (_list.isPresent()) {
                return new ResponseEntity(_list.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(localhost)
    @PostMapping(value = "/submit")
    public ResponseEntity submitQuiz(@RequestBody QuizSubmitRequest request) {
        try {
            Optional<Quiz> _quiz = quizRepository.findById(request.getQuiz().getId());

            if(_quiz.isPresent()) {
                int countCorrect = 0;
                List<OptionDto> answers =  request.getAnswered_options();
                for(int i=0; i< answers.size(); i++) {
                    Optional<Option> _option = questionOptionRepostory.findById(answers.get(i).getId());

                    if(_option.isPresent()) {
                        if(_option.get().isCorrect()) {
                            countCorrect++;
                        }
                    }
                }
                return new ResponseEntity(new QuizResultResponse("", countCorrect, request.getAnswered_options().size()), HttpStatus.OK);
            } else {
                return new ResponseEntity("QUIZ NOT FOUND", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
