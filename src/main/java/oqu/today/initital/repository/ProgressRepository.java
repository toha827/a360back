package oqu.today.initital.repository;

import oqu.today.initital.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
    Optional<Progress> findByLessonId(Long id);
    Optional<List<Progress>> findByUserId(Long id);
}
