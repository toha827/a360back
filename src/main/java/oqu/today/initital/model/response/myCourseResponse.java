package oqu.today.initital.model.response;

import oqu.today.initital.model.CourseUser;

import javax.persistence.Entity;
import java.util.List;

public class myCourseResponse {

    private List<CourseUser> data;

    public List<CourseUser> getData() {
        return data;
    }

    public void setData(List<CourseUser> data) {
        this.data = data;
    }
}
