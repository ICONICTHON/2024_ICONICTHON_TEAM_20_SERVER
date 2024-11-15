package dongguknuri.repository.course;

import dongguknuri.domain.course.CourseRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRecommendationRepository extends JpaRepository<CourseRecommendation, Long> {
}
