package oqu.today.initital.model.response;

import oqu.today.initital.model.Course;

import java.util.List;

public class DataCoursesResponse {
    private List<Course> data;

    public DataCoursesResponse(List<Course> data) {
        this.data = data;
    }

    public List<Course> getData() {
        return data;
    }

    public void setData(List<Course> data) {
        this.data = data;
    }
}
