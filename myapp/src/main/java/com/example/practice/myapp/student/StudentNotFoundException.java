package com.example.practice.myapp.student;

public class StudentNotFoundException extends RuntimeException {
    StudentNotFoundException(Long id){
        super("Student not found with id "+id);
    }
    
}
