package oqu.today.initital.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import oqu.today.initital.model.Course;
import oqu.today.initital.model.Teacher;
import oqu.today.initital.repository.CourseRepository;
import oqu.today.initital.repository.TeacherRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/javaApi/api/Teacher")
public class    TeacherRestController {

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping(value = "/readAll",produces = "application/json")
    public String getall() throws JSONException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Teacher> teachers = teacherRepository.findAll();
        String element = gson.toJson(
                teachers,
                new TypeToken<ArrayList<Teacher>>() {}.getType());
        return "{\n\t\t\"data\": " + element + "\n}";
    }

    @PostMapping(value="/load")
    public void persist(@RequestBody Teacher teacher){
        //int id, String name, String position, String info, int age, String email, String photo, String status, int course_id) {
        teacherRepository.save(new Teacher(teacher.getName(),teacher.getPosition(),teacher.getInfo(),teacher.getAge(),teacher.getEmail(),teacher.getPhoto(),teacher.getStatus()));
        return;
    }

//    @GetMapping(value = "/read",produces = "application/json")
//    public String getTeacherByCourse(@RequestParam int course_id) throws JSONException {
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        List<Teacher> teacher = teacherRepository.findByCourseId(course_id);
//        String element = gson.toJson(teacher,new TypeToken<ArrayList<Teacher>>() {}.getType());
//        return element == null ? "" : element;
//    }

    @GetMapping(value = "/delete")
    public void delete(@RequestParam int id){
        teacherRepository.deleteById((long) id);
    }
}
