package com.pamela.crudspring.controller;

import java.util.List;

import org.springframework.http.*;
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

    @GetMapping("/{id}")
    public ResponseEntity<CourseModel> findById(@PathVariable Long id){
        return courseRepository.findById(id)
        .map(recordFound -> ResponseEntity.ok().body(recordFound))
        .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CourseModel create(@RequestBody CourseModel courseModel) {
        return courseRepository.save(courseModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseModel> update(@PathVariable Long id, @RequestBody CourseModel courseModel){
        return courseRepository.findById(id)
        .map(recordFound -> {
            recordFound.setName(courseModel.getName());
            recordFound.setCategory(courseModel.getCategory());
            CourseModel updated =  courseRepository.save(recordFound);
            return ResponseEntity.ok().body(updated);
        })
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        return courseRepository.findById(id)
        .map(recordFound -> {
            courseRepository.deleteById(id);
            return ResponseEntity.noContent().<Void>build();
        })
        .orElse(ResponseEntity.notFound().build());
    }

}

