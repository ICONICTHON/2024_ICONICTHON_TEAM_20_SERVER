package dongguknuri.repository.course;

import dongguknuri.domain.course.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {
}
