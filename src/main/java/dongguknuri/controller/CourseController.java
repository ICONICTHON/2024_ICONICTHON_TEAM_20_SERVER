package dongguknuri.controller;

import dongguknuri.dto.global.ResponseDto;
import dongguknuri.dto.request.CreateCourseDto;
import dongguknuri.service.CourseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseDto<?> getAllCourses() {
        return ResponseDto.ok(courseService.getAllCourses());
    }

    @GetMapping("/{courseId}")
    public ResponseDto<?> getCourseById(@PathVariable Long courseId) {
        return ResponseDto.ok(courseService.getCourseById(courseId));
    }

    @PostMapping
    public ResponseDto<?> createCourse(@RequestBody CreateCourseDto createCourseDto){
        return ResponseDto.created(courseService.createCourse(createCourseDto));
    }

    @DeleteMapping("/{courseId}")
    public ResponseDto<?> deleteCourse(@PathVariable Long courseId){
        return ResponseDto.ok(courseService.deleteCourse(courseId));
    }
}
