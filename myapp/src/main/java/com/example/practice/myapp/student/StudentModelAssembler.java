package com.example.practice.myapp.student;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class StudentModelAssembler implements RepresentationModelAssembler<Student,EntityModel<Student>> {
    @Override
    public EntityModel<Student> toModel(Student student) {
        return EntityModel.of(
            student,
            linkTo(methodOn(StudentController.class).getStudent(student.getId())).withSelfRel(),
            linkTo(methodOn(StudentController.class).getStudents()).withRel("students"));
    }
}
