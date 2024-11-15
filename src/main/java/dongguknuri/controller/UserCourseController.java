package dongguknuri.controller;

import dongguknuri.dto.global.ResponseDto;
import dongguknuri.dto.request.CreateUserCourseDto;
import dongguknuri.service.UserCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user-courses")
public class UserCourseController {

    private final UserCourseService userCourseService;

    @PostMapping("/register")
    public ResponseDto<?> registerCourse(@RequestBody CreateUserCourseDto createUserCourseDto){
        return ResponseDto.created(userCourseService.registerCourse(createUserCourseDto));
    }

    @GetMapping("/user/{userId}/courses")
    public ResponseDto<?> getCoursesByUser(@PathVariable Long userId) {
        return ResponseDto.ok(userCourseService.getCoursesByUser(userId));
    }

    @GetMapping("/course/{courseId}/users")
    public ResponseDto<?> getUsersByCourse(@PathVariable Long courseId) {
        return ResponseDto.ok(userCourseService.getCoursesByCourse(courseId));
    }
}
