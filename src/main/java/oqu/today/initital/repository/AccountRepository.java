package oqu.today.initital.repository;

import oqu.today.initital.model.Account;
import oqu.today.initital.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long>{


//        @Query(value = "Select p from Profession p where p.key like %:sp%")
//        List<Course> findBySp(@Param("sp") String speciality);


}
