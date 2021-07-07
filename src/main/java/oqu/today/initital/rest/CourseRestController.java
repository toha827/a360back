package oqu.today.initital.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import oqu.today.initital.model.Course;
import oqu.today.initital.model.CourseUser;
import oqu.today.initital.model.response.myCourseResponse;
import oqu.today.initital.repository.CourseRepository;
import oqu.today.initital.repository.CourseUserRepository;
import oqu.today.initital.repository.LessonRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
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
    private CourseUserRepository courseUserRepository;

    final String localhost = "https://45.80.70.68";
    final String payment = "https://payment.oqu.today";
//    final String localhost = "http://localhost:3000";
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/readAll",produces = "application/json")
    public String getall() throws JSONException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject json = new JsonObject();
        List<Course> courses = courseRepository.findAll();
        String element = gson.toJson(
                courses,
                new TypeToken<ArrayList<Course>>() {}.getType());

        return "{ \"data\": " + element + "\n}";
    }

    @CrossOrigin(localhost)
    @PostMapping(value="/load")
    public void persist(@RequestBody Course course){
        courseRepository.save(course);
    }

    @CrossOrigin(payment)
    @PostMapping(value="/create")
    public ResponseEntity payment(@RequestBody CourseUser course){
        try {
            final Course _course = courseRepository.findByCourseId(course.getCourseId());
            _course.setLessons(lessonRepository.findByCourseId(course.getCourseId()));
            courseUserRepository.save(new CourseUser(course.getUserId(),course.getCourseId(),0, _course));
            return new ResponseEntity(course.getCourseId(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(localhost)
    @GetMapping(value = "/read",produces = "application/json")
    public ResponseEntity<myCourseResponse> getCourse(@RequestParam String user_id)  {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject json = new JsonObject();
            myCourseResponse response = new myCourseResponse();
            Optional<List<CourseUser>> courseUser = courseUserRepository.findByUserId(Integer.parseInt(user_id));
            //String element = gson.toJson(courseUser,CourseUser.class);
            if (courseUser.isPresent()) {
                List<Course> courses = new ArrayList<>();
                for (CourseUser temp : courseUser.get()) {
                    try {
                        Course course = courseRepository.findByCourseId(temp.getCourseId());
                        course.setProgress(temp.getProgress());
                        courses.add(course);
                    }catch (Exception e){
                        continue;
                    }
                }
//                String element = gson.toJson(courseUser.get(), new TypeToken<ArrayList<CourseUser>>() {}.getType());
                response.setData(courseUser.get());
                return new ResponseEntity(response, HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping(value = "/delete")
//    public void delete(@RequestParam int id){
//       courseRepository.deleteByCourseId(id);
//    }
}
