package dongguknuri.repository.course;

import dongguknuri.domain.User;
import dongguknuri.domain.course.Course;
import dongguknuri.domain.course.UserCourse;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {

    List<UserCourse> findByUser(User user);

    List<UserCourse> findByCourse(Course course);

    Optional<UserCourse> findByUserAndCourse(User user, Course course);
}
