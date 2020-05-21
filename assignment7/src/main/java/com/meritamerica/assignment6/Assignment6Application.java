package com.meritamerica.assignment6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.meritamerica.assignment7.repos.UsersRepository;

@SpringBootApplication
//Adding notation for JPA Repos , didnt have for prev assignment
@EnableJpaRepositories(basePackageClasses = UsersRepository.class)
public class Assignment6Application {

	public static void main(String[] args) {
		SpringApplication.run(Assignment6Application.class, args);
	}

}
