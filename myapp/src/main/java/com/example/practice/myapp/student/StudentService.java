package com.example.practice.myapp.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	private final StudentRepository studentRepository;
	private final StudentModelAssembler studentModelAssembler;
	public Object getStudent;
	
	@Autowired
	public StudentService(StudentRepository studentRepository, StudentModelAssembler studentModelAssembler){
		this.studentRepository = studentRepository;
		this.studentModelAssembler = studentModelAssembler;
	}
	
    CollectionModel<EntityModel<Student>> getStudents() {
		
		List<EntityModel<Student>> students = studentRepository.findAll().stream().map(studentModelAssembler::toModel).collect(Collectors.toList());
		return CollectionModel.of(students,linkTo(methodOn(StudentController.class).getStudents()).withSelfRel());
	}

	EntityModel<Student> getStudent(Long studentId) {
		Student student = studentRepository.findById(studentId).orElseThrow(()-> new StudentNotFoundException(studentId));
		return studentModelAssembler.toModel(student);		
	}

	public void addNewStudent(Student student) {
		Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
		if (studentByEmail.isPresent()){
			throw new IllegalStateException("Email is already taken");
		}
		studentRepository.save(student);
	}

	public void deleteStudent(Long studentId) {
		boolean exists = studentRepository.existsById(studentId);
		if(!exists){
			throw new StudentNotFoundException(studentId);
		}
		studentRepository.deleteById(studentId);
	}

	@Transactional
    public void updateStudent(Long studentId, 
							  String name, 
							  String email) {
		Student student = studentRepository.findById(studentId).orElseThrow(()-> new StudentNotFoundException(studentId));
		
		if (name!= null && name.length() >0 && !Objects.equals(student.getName(), name)){
			student.setName(name);
		}

		if(email != null && email.length() >0 && !Objects.equals(student.getEmail(), email)){
			Optional<Student> sOptional = studentRepository.findStudentByEmail(email);
			if (sOptional.isPresent()){
				throw new IllegalStateException("Emails is already taken!");
			}
			student.setEmail(email);
		}
    }  
}
