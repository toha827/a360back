package oqu.today.initital.repository;

import oqu.today.initital.model.Course;
import oqu.today.initital.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long>{


    Lesson findById(int id);
//        @Query(value = "Select p from Profession p where p.key like %:sp%")
//        List<Course> findBySp(@Param("sp") String speciality);
    List<Lesson> findByCourseId(int id);
    void deleteById(int id);
}
