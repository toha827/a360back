package oqu.today.initital.repository;

import oqu.today.initital.model.Option;
import oqu.today.initital.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionOptionRepostory extends JpaRepository<Option, Long> {

//    Optional<List<Question>> findAllByCourseId(Long id);
}
