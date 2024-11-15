package dongguknuri.service;

import dongguknuri.domain.User;
import dongguknuri.domain.course.Course;
import dongguknuri.domain.course.UserCourse;
import dongguknuri.dto.request.CreateUserCourseDto;
import dongguknuri.dto.response.UserCourseResponseDto;
import dongguknuri.exception.CommonException;
import dongguknuri.exception.ErrorCode;
import dongguknuri.repository.UserRepository;
import dongguknuri.repository.course.CourseRepository;
import dongguknuri.repository.course.UserCourseRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserCourseService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final UserCourseRepository userCourseRepository;

    @Transactional
    public boolean registerCourse(CreateUserCourseDto createUserCourseDto) {
        User user = userRepository.findById(createUserCourseDto.userId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        Course course = courseRepository.findById(createUserCourseDto.courseId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_COURSE));

        userCourseRepository.findByUserAndCourse(user,course).ifPresent(existingUserCourse -> {
            throw new IllegalStateException("이미 등록된 강의입니다.");
        });

        userCourseRepository.save(UserCourse.builder()
                .user(user)
                .course(course)
                .build());

        return Boolean.TRUE;
    }

    @Transactional(readOnly = true)
    public List<UserCourseResponseDto> getCoursesByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        return userCourseRepository.findByUser(user).stream()
                .map(c -> UserCourseResponseDto.of(
                        c.getUser().getUserId(),
                        c.getCourse().getCourseId()
                )).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<UserCourseResponseDto> getCoursesByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_COURSE));

        return userCourseRepository.findByCourse(course).stream()
                .map(c -> UserCourseResponseDto.of(
                        c.getUser().getUserId(),
                        c.getCourse().getCourseId()
                )).collect(Collectors.toList());
    }
}
