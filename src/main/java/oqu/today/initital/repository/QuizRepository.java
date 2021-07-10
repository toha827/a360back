package oqu.today.initital.repository;

import oqu.today.initital.model.Progress;
import oqu.today.initital.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Optional<List<Quiz>> findAllByCourseId(Long id);
}
