package oqu.today.initital.repository;

import oqu.today.initital.model.Course;
import oqu.today.initital.model.Lesson;
import oqu.today.initital.model.User;
import oqu.today.initital.model.request.LessonDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{


        @Query(value = "Select u from User u where u.email = :email and u.password = :password")
        User findByEmailPassword(@Param("email") String email, @Param("password") String password);
        User findByEmail(String email);

        @Query(value = "Select * from users u inner join users_purchaised p on u.user_id = p.user_user_id where p.user_user_id = :user and p.purchaised_id = :id", nativeQuery = true)
        Optional<User> findUsersByCourseId(@Param("id") long id, @Param("user") long user);

        @Query(value = "select distinct l.*, p.progress from lessons l\n" +
                "                inner join users_purchaised up on up.purchaised_id = l.course_id\n" +
                "                full join progresses p on l.id = p.lesson_id\n" +
                "                where up.user_user_id = :user", nativeQuery = true)
        Optional<List<LessonDTO>> findLessonsProgressByUser(@Param("user") long user);

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
