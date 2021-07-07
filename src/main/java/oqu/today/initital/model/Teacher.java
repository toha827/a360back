package oqu.today.initital.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String position;
    @Size(max = 4000)
    private String info;
    private int age;
    private String email;
    private String photo;
    @Size(max = 4000)
    private String status;
    private int courseId;

    public Teacher() {
    }

    public Teacher(int id, String name, String position, String info, int age, String email, String photo, String status, int course_id) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.info = info;
        this.age = age;
        this.email = email;
        this.photo = photo;
        this.status = status;
        this.courseId = course_id;
    }

    public Teacher(String name, String position, String info, int age, String email, String photo, String status, int course_id) {
        this.name = name;
        this.position = position;
        this.info = info;
        this.age = age;
        this.email = email;
        this.photo = photo;
        this.status = status;
        this.courseId = course_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", info='" + info + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                ", status='" + status + '\'' +
                ", course_id=" + courseId +
                '}';
    }
}