package dongguknuri.service;

import dongguknuri.domain.User;
import dongguknuri.domain.course.Course;
import dongguknuri.dto.request.CreateCourseDto;
import dongguknuri.dto.response.CourseResponseDto;
import dongguknuri.exception.CommonException;
import dongguknuri.exception.ErrorCode;
import dongguknuri.repository.UserRepository;
import dongguknuri.repository.course.CourseRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<CourseResponseDto> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(course -> CourseResponseDto.of(
                        course.getCourseId(),
                        course.getName(),
                        course.getDescription(),
                        course.getProfessor(),
                        course.getDay(),
                        course.getStartTime(),
                        course.getEndTime(),
                        course.getUser().getUserId()
                )).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CourseResponseDto getCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_COURSE));

        return CourseResponseDto.builder()
                .id(course.getCourseId())
                .name(course.getName())
                .description(course.getDescription())
                .professor(course.getProfessor())
                .day(course.getDay())
                .startTime(course.getStartTime())
                .endTime(course.getEndTime())
                .userId(course.getUser().getUserId())
                .build();
    }

    @Transactional
    public boolean createCourse(CreateCourseDto createCourseDto) {
        User user = userRepository.findById(createCourseDto.userId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        courseRepository.save(Course.builder()
                .name(createCourseDto.name())
                .description(createCourseDto.description())
                .professor(createCourseDto.professor())
                .day(createCourseDto.day())
                .startTime(createCourseDto.startTime())
                .endTime(createCourseDto.endTime())
                .user(user)
                .build());
        return Boolean.TRUE;
    }

    @Transactional
    public boolean deleteCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_COURSE));

        courseRepository.delete(course);
        return Boolean.TRUE;
    }
}
