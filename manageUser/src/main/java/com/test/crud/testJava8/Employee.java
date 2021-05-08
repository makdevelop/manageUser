package com.test.crud.testJava8;

public class Employee {
	
	private int id;
	
	private String nom;
	
	private int age;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Employee(int id, String nom, int age) {
		super();
		this.id = id;
		this.nom = nom;
		this.age = age;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int salaryIncrement(int salary){
		
		return age+salary;
		
	}
	

}
