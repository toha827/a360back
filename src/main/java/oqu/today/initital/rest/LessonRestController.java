package oqu.today.initital.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import oqu.today.initital.model.*;
import oqu.today.initital.model.response.UserUpdateResponse;
import oqu.today.initital.repository.*;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/javaApi/api/Lessons")
public class LessonRestController {

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


    final String localhost = "https://45.80.70.68";
//    final String localhost = "http://localhost:3000";

    @CrossOrigin(localhost)
    @GetMapping(value = "/readAll",produces = "application/json")
    public String getall() throws JSONException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Lesson> teachers = lessonRepository.findAll();
        String element = gson.toJson(
                teachers,
                new TypeToken<ArrayList<Lesson>>() {}.getType());
        return "{\n\t\t\"data\": " + element + "\n}";
    }

    @CrossOrigin(localhost)
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
                    return new ResponseEntity(lesson.get(), HttpStatus.OK);
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

    @CrossOrigin(localhost)
    @PostMapping(value="/load")
    public ResponseEntity persist(@RequestBody Lesson lesson){
        Optional<Teacher> _teacher = teacherRepository.findById(lesson.getTeacher().getId());
        Optional<Course> _course = courseRepository.findById(lesson.getCourse().getId());
        if(_teacher.isPresent()) {
            if(_course.isPresent()) {
                lessonRepository.save(new Lesson(lesson.getTitle(), lesson.getVideo(), lesson.getNumber(), lesson.getBlock(), lesson.getLang(), lesson.getCourse(), lesson.getTeacher(), lesson.getDuration(), lesson.getChapter(), 0));
                return new ResponseEntity(HttpStatus.OK);
            }
            return new ResponseEntity(new UserUpdateResponse("Course not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(new UserUpdateResponse("Teacher not found"), HttpStatus.NOT_FOUND);
    }

    @CrossOrigin(localhost)
    @GetMapping(value="/read", produces = "application/json")
    public String read(@RequestParam int id){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Lesson> lessons = lessonRepository.findByCourseId(id);
        String element = gson.toJson(
                lessons,
                new TypeToken<ArrayList<Lesson>>() {}.getType());

        return "{\n\t\t\"data\": " + element + "\n}";
    }

    @GetMapping(value = "/delete")
    public void delete(@RequestParam int id){
        lessonRepository.deleteById((long) id);
    }
}
