package oqu.today.initital.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import oqu.today.initital.model.Course;
import oqu.today.initital.model.CourseUser;
import oqu.today.initital.model.Lesson;
import oqu.today.initital.model.Teacher;
import oqu.today.initital.repository.CourseRepository;
import oqu.today.initital.repository.CourseUserRepository;
import oqu.today.initital.repository.LessonRepository;
import oqu.today.initital.repository.TeacherRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
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
    private CourseUserRepository courseUserRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private TeacherRepository teacherRepository;


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
    public String readLessonById(@RequestParam(name = "id") int id, @RequestParam(name = "user_id") int user_id) throws JSONException {
        try {
            Optional<List<CourseUser>> optionalList = courseUserRepository.findByUserId(user_id);

            if(optionalList.isPresent()) {
                List<CourseUser> list = optionalList.get();
                for (int i = 0; i < list.size(); i++) {
                    Course _course = list.get(i).getCourse();
                    List<Lesson> _list = _course.getLessons();
                    for (int j = 0; j < _list.size(); j++) {
                        if(_list.get(j).getId() == id){
                            _list.get(j).setProgress(100);
                        }
                    }
                    _course.setLessons(_list);
                    list.get(i).setCourse(_course);
                }
                courseUserRepository.saveAll(list);
            }

            Lesson lesson = lessonRepository.findById(id);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json1 = gson.toJson(lesson);
            System.out.println(lesson.toString());

            Teacher teacher = teacherRepository.findById(lesson.getTeacherId());
            String json2 = gson.toJson(teacher);
            System.out.println(teacher.toString());

            Course course = courseRepository.findByCourseId(lesson.getCourseId());
            String json3 = gson.toJson(course);
            System.out.println(course.toString());

            return "{\n \"lesson\": " + json1 + "\n" +
                    ", \"teacher\": " + json2 + "\n" +
                    ", \"course\": " + json3 + "}";
        }catch (Exception e){
            return e.toString();
        }
    }

    @CrossOrigin(localhost)
    @PostMapping(value="/load")
    public void persist(@RequestBody Lesson lesson){
        //int id, String title, String link, int number, String block, String lang, int course_id, int teacher_id, int duration
        if(teacherRepository.findById(lesson.getTeacherId()) != null && courseRepository.findByCourseId(lesson.getCourseId()) != null) {
            lessonRepository.save(new Lesson(lesson.getTitle(), lesson.getVideo(), lesson.getNumber(), lesson.getBlock(), lesson.getLang(), lesson.getCourseId(), lesson.getTeacherId(), lesson.getDuration(), lesson.getCourseType(), 0));
        }
        return;
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
