package oqu.today.initital.repository;

import oqu.today.initital.model.Course;
import oqu.today.initital.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{


        @Query(value = "Select u from User u where u.email = :email and u.password = :password")
        User findByEmailPassword(@Param("email") String email, @Param("password") String password);
        User findByEmail(String email);

        @Transactional
        @Modifying
        @Query(value = "Update User set name = :name, email = :email, password = :password, gender = :gender, avatar = :avatar, phone = :phone,bday = :bday where user_id=:id")
        void updateUser(
                @Param("id") long id,
                @Param("name") String name,
                    @Param("email") String email,
                    @Param("password") String password,
                    @Param("gender") String gender,
                    @Param("avatar") String avatar,
                    @Param("bday") Date bday,
                    @Param("phone") String phone);

        void deleteById(long id);
}
