package com.example.practice.myapp.student;

import java.time.LocalDate;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
    
	@Bean
    CommandLineRunner commandLineRunner(StudentRepository student){
        return args -> {

			Student kushal = new Student(
				"kushal",
				"kushal@gmail.com",
				LocalDate.of(2000, 02, 12)
			);

            Student happy = new Student(
				"happy",
				"happy@gmail.com",
				LocalDate.of(2000, 10, 21)
			);

            student.saveAll(List.of(kushal,happy));
        };
    }
}
