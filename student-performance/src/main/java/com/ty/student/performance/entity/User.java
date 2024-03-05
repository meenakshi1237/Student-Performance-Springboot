package com.ty.student.performance.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ty.student.performance.util.UserRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@Size(min = 4,message = "Name must be more than 3 characters")
	private String userName;
	@Column(nullable = false,unique = true)
	@Email(message = "invalid Email format")
	private String email;
	@Size(min = 8,max = 16)
	private String password;
	@Enumerated(EnumType.STRING)
	private UserRole userRole;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Presentation> presentations = new ArrayList<Presentation>();
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Voting> votings;
	

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private UserProfile userProfile;
	
}
