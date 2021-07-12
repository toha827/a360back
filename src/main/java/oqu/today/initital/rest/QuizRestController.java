package oqu.today.initital.rest;

import oqu.today.initital.model.Chapter;
import oqu.today.initital.model.Course;
import oqu.today.initital.model.Question;
import oqu.today.initital.model.Quiz;
import oqu.today.initital.model.response.UserUpdateResponse;
import oqu.today.initital.repository.ChapterRepository;
import oqu.today.initital.repository.CourseRepository;
import oqu.today.initital.repository.QuestionRepository;
import oqu.today.initital.repository.QuizRepository;
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
    private CourseRepository courseRepository;

    @Autowired
    private ChapterRepository chapterRepository;


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
}
