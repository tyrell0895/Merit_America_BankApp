package com.meritamerica.assignment7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.meritamerica.assignment7.repos.UsersRepository;

@SpringBootApplication
//Adding notation for JPA Repos , didnt have for prev assignment
@EnableJpaRepositories(basePackageClasses = UsersRepository.class)
public class Assignment7Application {

	public static void main(String[] args) {
		SpringApplication.run(Assignment7Application.class, args);
	}

}
