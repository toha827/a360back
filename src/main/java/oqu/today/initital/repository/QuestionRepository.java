package oqu.today.initital.repository;

import oqu.today.initital.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Optional<List<Question>> findAllByCourseCourseId(Long id);
}
