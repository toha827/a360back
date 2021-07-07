package oqu.today.initital.repository;

import oqu.today.initital.model.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {
    public void deleteByUserId(int userId);
}
