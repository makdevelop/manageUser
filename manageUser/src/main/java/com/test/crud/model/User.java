package com.test.crud.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "user_tab")
public class User implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private Long id;
	
    
	@Column(name = "nom" )
	@NotNull
	@NotEmpty(message = "champ nom obligatoire")
	private String nom;
	
	@Column(name = "prenom")
	@NotNull
	@NotEmpty(message = "champ prenom obligatoire")
	private String prenom;
	
	@Column(name = "email")
	@Email(message = "Email invalid !!")
    private String email;
	
	@Column(name = "age")
	@Min(value = 18,message = "L'âge ne doit pas être inférieur à 18 ans")
	@NotNull
	private int age;
	
	
	@Column(name = "adress")
	@NotNull
	@NotEmpty(message = "champ adresse obligatoire")
	private String adress;
	
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Long id, String nom, String prenom, String email, int age,  String adress) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.age = age;

		this.adress = adress;
	}
	
	public User(String nom, String prenom, String email, int age, String adress) {
		super();		
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.age = age;		
		this.adress = adress;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	
	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", age=" + age
				+ ", adress=" + adress + "]";
	}

	
	
	
}
