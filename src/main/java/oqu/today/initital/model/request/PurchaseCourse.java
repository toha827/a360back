package oqu.today.initital.model.request;

import javax.persistence.Entity;
import javax.persistence.Id;

public class PurchaseCourse {
    @Id
    private int id;

    private long user_id;

    private int course_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
}
