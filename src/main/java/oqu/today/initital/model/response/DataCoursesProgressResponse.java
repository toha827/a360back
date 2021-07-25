package oqu.today.initital.model.response;

import oqu.today.initital.model.Course;
import oqu.today.initital.model.Progress;

import java.util.List;

public class DataCoursesProgressResponse {
    private List<Course> data;
    private List<Progress> progressList;


    public DataCoursesProgressResponse(List<Course> data, List<Progress> progressList) {
        this.data = data;
        this.progressList = progressList;
    }

    public List<Course> getData() {
        return data;
    }

    public void setData(List<Course> data) {
        this.data = data;
    }

    public List<Progress> getProgressList() {
        return progressList;
    }

    public void setProgressList(List<Progress> progressList) {
        this.progressList = progressList;
    }
}
