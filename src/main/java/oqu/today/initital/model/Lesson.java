package oqu.today.initital.model;

import javax.persistence.*;
@Entity
@Table(name = "Lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    @Column(name = "link")
    private String video;
    private int number;
    private String block;
    private String lang;
    @Column(name = "course_id")
    private int courseId;
    @Column(name = "teacher_id")
    private int teacherId;
    private String duration;
    private String courseType;
    private int progress;

    public Lesson() {
    }

    public Lesson(int id, String title, String video, int number, String block, String lang, int courseId, int teacherId, String duration, String courseType, int progress) {
        this.id = id;
        this.title = title;
        this.video = video;
        this.number = number;
        this.block = block;
        this.lang = lang;
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.duration = duration;
        this.courseType = courseType;
        this.progress = progress;
    }

    public Lesson(String title, String video, int number, String block, String lang, int courseId, int teacherId, String duration, String courseType, int progress) {
        this.title = title;
        this.video = video;
        this.number = number;
        this.block = block;
        this.lang = lang;
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.duration = duration;
        this.progress = progress;
        ;
        this.courseType = courseType;
    }


    public int getId() {
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

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", link='" + video + '\'' +
                ", number=" + number +
                ", block='" + block + '\'' +
                ", lang='" + lang + '\'' +
                ", course_id=" + courseId +
                ", teacher_id=" + teacherId +
                ", duration=" + duration +
                '}';
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
