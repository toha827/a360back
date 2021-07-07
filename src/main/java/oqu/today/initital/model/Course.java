package oqu.today.initital.model;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int courseId;
    private String name;
    private String subject;
    private String image;
    @Size(max = 4000)
    private String info;
    private int num_of_lessons;
    private String toLearn;
    private String toKnow;
    private int teacher_id;
    private double price;
    private int progress;
    @ManyToMany
    private List<Lesson> lessons;
    @OneToMany
    private List<Question> questions;

    public Course(int courseId, String name, String subject, String image, String info, int num_of_lessons, String toLearn, String toKnow, int teacher_id, double price, List<Lesson> lessons, List<Question> questions) {
        this.courseId = courseId;
        this.name = name;
        this.subject = subject;
        this.image = image;
        this.info = info;
        this.num_of_lessons = num_of_lessons;
        this.toLearn = toLearn;
        this.toKnow = toKnow;
        this.teacher_id = teacher_id;
        this.price = price;
        this.lessons = lessons;
        this.questions = questions;
    }

    public Course() {
    }

    public Course(String name, String subject, String image, String info, int num_of_lessons, String toLearn, String toKnow, int teacher_id, double price, List<Lesson> lessons, List<Question> questions) {
        this.name = name;
        this.subject = subject;
        this.image = image;
        this.info = info;
        this.num_of_lessons = num_of_lessons;
        this.toLearn = toLearn;
        this.toKnow = toKnow;
        this.teacher_id = teacher_id;
        this.price = price;
        this.lessons = lessons;
        this.questions = questions;
    }


    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int course_id) {
        this.courseId = course_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getToLearn() {
        return toLearn;
    }

    public void setToLearn(String toLearn) {
        this.toLearn = toLearn;
    }

    public int getNum_of_lessons() {
        return num_of_lessons;
    }

    public void setNum_of_lessons(int num_of_lessons) {
        this.num_of_lessons = num_of_lessons;
    }

    public String getToKnow() {
        return toKnow;
    }

    public void setToKnow(String toKnow) {
        this.toKnow = toKnow;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courser_id=" + courseId +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", image='" + image + '\'' +
                ", info='" + info + '\'' +
                ", num_of_lessons=" + num_of_lessons +
                ", toLearn='" + toLearn + '\'' +
                ", toKnow='" + toKnow + '\'' +
                ", teacher_id=" + teacher_id +
                ", price=" + price +
                '}';
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
