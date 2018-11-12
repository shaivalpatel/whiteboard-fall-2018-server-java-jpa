package com.example.whiteboardfall2018shaivlpatelmiddletierjava.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id ;
	@Column(unique=true)
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
	@OneToMany(mappedBy="user")
	private List<Course> courses = new ArrayList<Course>();

	public User() {}
	public User(String username) {
		this.username = username;
	}
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public User(String firstName, String lastName, String username, String password) {
		this(username, password);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
