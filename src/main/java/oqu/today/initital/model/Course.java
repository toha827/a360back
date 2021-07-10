package oqu.today.initital.model;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
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
    @OneToMany
    @JoinColumn(name = "course_id")
    private List<Lesson> lessons;
    @OneToMany
    private List<Quiz> quizes;

    public Course(String name, String subject, String image, @Size(max = 4000) String info, int num_of_lessons, String toLearn, String toKnow, int teacher_id, double price, int progress, List<Lesson> lessons, List<Quiz> quizes) {
        this.name = name;
        this.subject = subject;
        this.image = image;
        this.info = info;
        this.num_of_lessons = num_of_lessons;
        this.toLearn = toLearn;
        this.toKnow = toKnow;
        this.teacher_id = teacher_id;
        this.price = price;
        this.progress = progress;
        this.lessons = lessons;
        this.quizes = quizes;
    }

    public Course() {
    }

    public long getId() {
        return id;
    }

    public void setId(int course_id) {
        this.id = course_id;
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

    public List<Quiz> getQuizes() {
        return quizes;
    }

    public void setQuizes(List<Quiz> quizes) {
        this.quizes = quizes;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", image='" + image + '\'' +
                ", info='" + info + '\'' +
                ", num_of_lessons=" + num_of_lessons +
                ", toLearn='" + toLearn + '\'' +
                ", toKnow='" + toKnow + '\'' +
                ", teacher_id=" + teacher_id +
                ", price=" + price +
                ", progress=" + progress +
                ", lessons=" + lessons +
                ", quizes=" + quizes +
                '}';
    }
}
