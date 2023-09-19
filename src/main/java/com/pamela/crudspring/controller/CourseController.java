package com.pamela.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.pamela.crudspring.model.CourseModel;

import lombok.AllArgsConstructor;
import com.pamela.crudspring.repository.CourseRepository;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;

    // @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public @ResponseBody List<CourseModel> list() {
        return courseRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CourseModel create(@RequestBody CourseModel courseModel) {
        return courseRepository.save(courseModel);
    }
}
