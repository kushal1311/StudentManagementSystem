// Repository for Student class
package com.example.practice.myapp.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

    @Query("SELECT s From Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
    
}
