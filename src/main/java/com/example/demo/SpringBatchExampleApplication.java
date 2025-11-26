package com.example.demo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.demo.entity.Person;

@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
public class SpringBatchExampleApplication {

	public static void main(String[] args) {

//		// DEMO DATA
		List<Person> list = Arrays.asList(new Person(10, "Arun"), new Person(3, "John"), new Person(7, "Priya"),
				new Person(1, "Karan"));

		Set<Person> sortedSet = new TreeSet<Person>(Comparator.comparing(p -> p.getName()));
		sortedSet.addAll(list);
		sortedSet.stream().forEach(System.out::println);

		// Given an array of integers, find the pair of numbers that sum up to a target
		// value

		int[] intArr = { 1, 2, 3, 4, 5, 6, 7 };
		int target = 7;

		for (int i = 0; i < intArr.length - 1; i++) {
			for (int j = i; j < intArr.length; j++) {
				if (intArr[i] + intArr[j] == target) {
					System.out.println(intArr[i] + "====" + intArr[j]);
				}
			}

		}

		Map<Integer, Integer> targetMap = new HashMap<>();

		for (int i = 0; i < intArr.length; i++) {
			int compareValue = target - intArr[i];
			if (targetMap.containsKey(compareValue)) {
				System.out.println(compareValue + "====" + intArr[i]);
			}
			targetMap.put(intArr[i], i);
		}

		// SpringApplication.run(SpringBatchExampleApplication.class, args);
	}

}
