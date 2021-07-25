package oqu.today.initital.model.response;

import oqu.today.initital.model.Lesson;
import oqu.today.initital.model.request.LessonDTO;

import java.util.List;

public class DataLessonsResponse {
    private List<LessonDTO> data;

    public DataLessonsResponse(List<LessonDTO> data) {
        this.data = data;
    }

    public List<LessonDTO> getData() {
        return data;
    }

    public void setData(List<LessonDTO> data) {
        this.data = data;
    }
}
