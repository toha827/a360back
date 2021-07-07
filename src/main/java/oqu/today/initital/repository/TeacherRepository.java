package oqu.today.initital.repository;

import oqu.today.initital.model.Course;
import oqu.today.initital.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long>{

    Teacher findById(int id);
    List<Teacher> findByCourseId(int id);
//        @Query(value = "Select p from Profession p where p.key like %:sp%")
//        List<Course> findBySp(@Param("sp") String speciality);
    void deleteById(int id);

}
