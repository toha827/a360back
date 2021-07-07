package oqu.today.initital.repository;

import oqu.today.initital.model.Course;
import oqu.today.initital.model.CourseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseUserRepository extends JpaRepository<CourseUser,Long>{


//        @Query(value = "Select p from Profession p where p.key like %:sp%")
//        List<Course> findBySp(@Param("sp") String speciality);

    Optional<List<CourseUser>> findByUserId(int user_id);
}
