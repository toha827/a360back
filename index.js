  import axios from "axios";
const endpoint = "https://localhost:8000/javaApi/api";

export const loginAPI = user => {
  return axios.post(endpoint + "/Profile/checklogin", user, {
    headers: {
      "Content-Type": "application/json"
    }
  });
};

export const signUpApi = user => {
  return axios.post(endpoint + "/Profile/create", user, {
    headers: {
      "Content-Type": "application/json"
    }
  });
};

export const getLessons = () => {
  return axios.get(endpoint + "/MyCourses/readAll");
};

export const getMyLessons = (id) =>{
  return axios.get(endpoint + "/MyCourses/read?user_id=" + id);
}

export const getLessonsOfCourse = (id) => {
  return axios.get(endpoint + "/Lessons/read?id=" + id);
} 


export const getLessonByIdApi = (id) => {
  return axios.get(endpoint + "/Lessons/readLesson?id=" + id);
} 