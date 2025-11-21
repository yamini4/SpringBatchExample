package com.example.demo.entity;

import lombok.Getter;

@Getter
public class Person implements Comparable<Person> {
	int id;
	String name;

	public Person(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public int compareTo(Person o) {
		return this.id - o.id;
	}

	@Override
	public String toString() {
		return id + " - " + name;
	}
}
