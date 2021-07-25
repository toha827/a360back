package oqu.today.initital.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import oqu.today.initital.model.Course;
import oqu.today.initital.model.Progress;
import oqu.today.initital.model.User;
import oqu.today.initital.model.request.LessonDTO;
import oqu.today.initital.model.request.PurchaseCourse;
import oqu.today.initital.model.response.DataCoursesProgressResponse;
import oqu.today.initital.model.response.DataCoursesResponse;
import oqu.today.initital.repository.CourseRepository;
import oqu.today.initital.repository.LessonRepository;
import oqu.today.initital.repository.ProgressRepository;
import oqu.today.initital.repository.UserRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/javaApi/api/MyCourses")
public class CourseRestController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private ProgressRepository progressRepository;

    @Autowired
    private UserRepository userRepository;

//    final String localhost = "http://45.80.70.68";
    final String payment = "https://payment.oqu.today";

    @Value("${hostname}")
    final String localhost = "http://localhost:3000";

    @CrossOrigin(localhost)
    @GetMapping(value = "/readAll")
    public ResponseEntity getall()  {
        try {
            List<Course> courses = courseRepository.findAll();

            return new ResponseEntity(new DataCoursesResponse(courses), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value="/load")
    public void persist(@RequestBody Course course){
        courseRepository.save(course);
    }

    @CrossOrigin(payment)
    @PostMapping(value="/create")
    public ResponseEntity payment(@RequestBody PurchaseCourse purchase){
        try {
            final Course _course = courseRepository.findByCourseId(purchase.getCourse_id());

             final Optional<User> _user = userRepository.findById(purchase.getUser_id());
            if(_user.isPresent()) {

                final List<Course> _temp = _user.get().getPurchaised();
                _temp.add(_course);
                _user.get().setPurchaised(_temp);
                userRepository.save(_user.get());

                return new ResponseEntity(_course, HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
//
    @CrossOrigin(localhost)
    @GetMapping(value = "/read",produces = "application/json")
    public ResponseEntity getCourse(@RequestParam long user_id)  {
        try {
            final Optional<User> _user = userRepository.findById(user_id);
            final Optional<List<Progress>> _progresses = progressRepository.findByUserId(user_id);
           if (_user.isPresent()) {
               if(_progresses.isPresent()){
                   return new ResponseEntity(new DataCoursesProgressResponse(_user.get().getPurchaised(), _progresses.get()), HttpStatus.OK);
               }
               else {
                   return new ResponseEntity(new DataCoursesProgressResponse(_user.get().getPurchaised(), new ArrayList<Progress>()), HttpStatus.OK);
               }
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping(value = "/delete")
//    public void delete(@RequestParam int id){
//       courseRepository.deleteByCourseId(id);
//    }
}
