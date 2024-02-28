package com.ty.student.performance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "upro_id_gen")
	@SequenceGenerator(name = "upro_id_gen",initialValue = 401,allocationSize = 1)
	private int profileId;
	
	private String path;
	
	@OneToOne
	@JoinColumn
	private User user;
	
}
