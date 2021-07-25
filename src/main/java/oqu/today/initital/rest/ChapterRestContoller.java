package oqu.today.initital.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import oqu.today.initital.model.*;
import oqu.today.initital.model.request.LessonDTO;
import oqu.today.initital.model.response.DataLessonsResponse;
import oqu.today.initital.model.response.UserUpdateResponse;
import oqu.today.initital.repository.*;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/javaApi/api/chapter")
public class ChapterRestContoller {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ProgressRepository progressRepository;

    @Autowired
    private ChapterRepository chapterRepository;

    private static final Logger logger = LoggerFactory.getLogger(FileRestContoller.class);

    @Value("${hostname}")
    final String localhost = "http://45.80.70.68";
//    final String localhost = "http://localhost:3000";

    @PostMapping
    public ResponseEntity create(@RequestBody Chapter chapter){
        try {
            logger.info(chapter.toString());
            Chapter _chapter = chapterRepository.save(chapter);
            return new ResponseEntity(_chapter, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new UserUpdateResponse(e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/all",produces = "application/json")
    public ResponseEntity getall() {
        return new ResponseEntity(chapterRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/readLesson",produces = "application/json")
    public ResponseEntity readLessonById(@RequestParam(name = "id") long id, @RequestParam(name = "user_id") long user_id){
        try {
            Optional<User> _user = userRepository.findById(user_id);
            Optional<Lesson> lesson = lessonRepository.findById(id);

            if(lesson.isPresent()) {
                if(_user.isPresent()) {
                    List<Course> list = _user.get().getPurchaised();
                    Optional<Progress> _progress = progressRepository.findByLessonId(id);

                    if(_progress.isPresent()) {
                        Progress _newProgress = _progress.get();
                        _newProgress.setProgress(100);
                        progressRepository.save(_newProgress);
                    } else {
                        Progress _newProgress = new Progress(lesson.get(), _user.get(), 100);
                        progressRepository.save(_newProgress);
                    }
                    return new ResponseEntity(lesson.get().toDto(), HttpStatus.OK);
                } else {
                    return new ResponseEntity(HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            return new ResponseEntity(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping(value="/read", produces = "application/json")
    public ResponseEntity read(@RequestParam int id){
        try {
            Optional<List<Lesson>> lessons = lessonRepository.findByCourseIdOrderById(id);
            if (lessons.isPresent()) {
                return new ResponseEntity(new DataLessonsResponse(lessons.get().stream().map(it -> it.toDto()).collect(Collectors.toList())), HttpStatus.OK);
            } else {
                return new ResponseEntity("Lesson not found", HttpStatus.NOT_FOUND);
            }
            } catch (Exception e ){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/delete")
    public void delete(@RequestParam int id){
        lessonRepository.deleteById((long) id);
    }
}
