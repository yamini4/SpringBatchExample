package com.example.demo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.demo.entity.Employee;
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

		///////////////////////////////////////////////////
		List<Employee> listEmp = Arrays.asList(new Employee(10, "Arun", 3473097, "ban", 'F'),
				new Employee(3, "John", 3473097, "htd", 'F'), new Employee(7, "Priya", 3473097, "ban", 'M'),
				new Employee(1, "Karan", 3473097, "ban", 'M'));

		// ASC Sorting by Name
		List<Employee> sortAsc = listEmp.stream().sorted(Comparator.comparing(Employee::getName))
				.collect(Collectors.toList());
		sortAsc.forEach(System.out::println);

		// DESC Sorting by Name
		List<Employee> sortDesc = listEmp.stream().sorted(Comparator.comparing(Employee::getName).reversed())
				.collect(Collectors.toList());
		sortDesc.forEach(System.out::println);

		// Modify salary based on condition
		List<Employee> modifiedEmp = listEmp.stream().map(e -> {
			if (e.getSalary() <= 50000) {
				e.setSalary(e.getSalary() + 10000);
			}
			return e;
		}).collect(Collectors.toList());
		modifiedEmp.forEach(System.out::println);

		// Sum of salary for employees from "ban"
		long sumOfSalary = listEmp.stream().filter(e -> "ban".equals(e.getLocation())).mapToLong(Employee::getSalary)
				.sum();

		System.out.println("Sum of salary (ban): " + sumOfSalary);

		// Count male and female employees
		long countFemale = listEmp.stream().filter(e -> e.getGender() == 'F').count();

		long countMale = listEmp.stream().filter(e -> e.getGender() == 'M').count();

		System.out.println("Female Count: " + countFemale);
		System.out.println("Male Count: " + countMale);

		SpringApplication.run(SpringBatchExampleApplication.class, args);

	}

}
