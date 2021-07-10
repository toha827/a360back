package oqu.today.initital.repository;

import oqu.today.initital.model.Course;
import oqu.today.initital.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long>{
}
