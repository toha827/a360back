package oqu.today.initital.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import oqu.today.initital.model.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class LessonDTO implements Serializable {
    private long id;
    private String title;
    private String video;
    private int number;
    private String block;
    private String lang;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToOne
    private Teacher teacher;
    private String duration;
    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Chapter chapter;
    private int progress;

    public LessonDTO() {
    }

    public LessonDTO(String title, String video, int number, String block, String lang, Course course, Teacher teacher, String duration, Chapter chapter, int progress) {
        this.title = title;
        this.video = video;
        this.number = number;
        this.block = block;
        this.lang = lang;
        this.course = course;
        this.teacher = teacher;
        this.duration = duration;
        this.chapter = chapter;
        this.progress = progress;
    }

    public LessonDTO(long id, String title, String video, int number, String block, String lang, Course course, Teacher teacher, String duration, Chapter chapter, int progress) {
        this.id = id;
        this.title = title;
        this.video = video;
        this.number = number;
        this.block = block;
        this.lang = lang;
        this.course = course;
        this.teacher = teacher;
        this.duration = duration;
        this.chapter = chapter;
        this.progress = progress;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public Lesson toEntity() {
        return new Lesson(
                this.getTitle(),
                this.getVideo(),
                this.getNumber(),
                this.getBlock(),
                this.getLang(),
                this.getCourse(),
                this.getTeacher(),
                this.getDuration(),
                this.getChapter());
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", video='" + video + '\'' +
                ", number=" + number +
                ", block='" + block + '\'' +
                ", lang='" + lang + '\'' +
                ", course=" + course +
                ", teacher=" + teacher +
                ", duration='" + duration + '\'' +
                ", chapter=" + chapter +
                ", progress=" + progress +
                '}';
    }
}
