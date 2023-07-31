package com.healthcare.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



@Entity
@Table
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userid;
	
	@NotBlank(message= "name field is required")
    @Size(min=2,max=20,message="min 2 and max 20 characters are allowed")
	private String name;
	
	@NotBlank(message= "address field is required")
	private String address;
	
	@NotBlank(message= "phoneno field is required")
	private String phoneno;
	
	
	@Column(unique=true)
	@Pattern(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message="invalid email")
	@NotBlank(message= "email field is required")
	private String email;
	
	@NotBlank(message= "password field is required")
	private String password;
	
	private String birthdate;
	private String Role;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="user",orphanRemoval=true)
	private List<Policy> policies;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User(int userid, String name, String address, String phoneno, String email, String birthdate) {
		super();
		this.userid = userid;
		this.name = name;
		this.address = address;
		this.phoneno = phoneno;
		this.email = email;
		this.birthdate = birthdate;
	}


	public int getUserid() {
		return userid;
	}


	public void setUserid(int userid) {
		this.userid = userid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhoneno() {
		return phoneno;
	}


	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getBirthdate() {
		return birthdate;
	}


	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}


	public List<Policy> getPolicies() {
		return policies;
	}


	public void setPolicies(List<Policy> policies) {
		this.policies = policies;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRole() {
		return Role;
	}


	public void setRole(String role) {
		Role = role;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
