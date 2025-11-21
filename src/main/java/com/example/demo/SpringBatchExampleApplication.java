package com.example.demo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.demo.entity.Person;

@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
public class SpringBatchExampleApplication {

	public static void main(String[] args) {

		// DEMO DATA
		List<Person> list = Arrays.asList(new Person(10, "Arun"), new Person(3, "John"), new Person(7, "Priya"),
				new Person(1, "Karan"));

		Set<Person> sortedSet = new TreeSet<Person>(Comparator.comparing(p -> p.getName()));
		sortedSet.addAll(list);
		sortedSet.stream().forEach(System.out::println);

		SpringApplication.run(SpringBatchExampleApplication.class, args);
	}

}
