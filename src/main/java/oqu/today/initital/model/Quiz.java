package oqu.today.initital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "quizes")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String description;

    @OneToOne
    private Chapter chapter;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany
    @JoinColumn(name = "quiz_id")
    private List<Question> questions;

    public Quiz(long id, String name, String description, Chapter chapter, Course course, List<Question> questions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.chapter = chapter;
        this.course = course;
        this.questions = questions;
    }

    public Quiz(String name, String description, Chapter chapter, Course course, List<Question> questions) {
        this.name = name;
        this.description = description;
        this.chapter = chapter;
        this.course = course;
        this.questions = questions;
    }

    public Quiz() {
    }

    public long getId() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", chapter=" + chapter +
                ", course=" + course +
                ", questions=" + questions +
                '}';
    }
}
