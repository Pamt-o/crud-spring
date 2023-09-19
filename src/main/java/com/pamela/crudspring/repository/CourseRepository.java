package com.pamela.crudspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pamela.crudspring.model.CourseModel;

public interface CourseRepository extends JpaRepository<CourseModel, Long>{
    
}
