package oqu.today.initital.repository;

import oqu.today.initital.model.Chapter;
import oqu.today.initital.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter,Long>{
}
