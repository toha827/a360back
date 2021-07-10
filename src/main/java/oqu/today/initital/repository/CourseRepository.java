package oqu.today.initital.repository;

import oqu.today.initital.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CourseRepository extends JpaRepository<Course,Long>{

    @Query(value = "Select p from Course p where p.id=:id")
    Course findByCourseId(@Param("id") int id);
//    void deleteByCourseId(int id);

}
