package com.ty.student.performance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Voting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "vote_id_gen",initialValue = 601,allocationSize = 1)
	private int votingId;
	private String review;
	private String suggestion;
	
	@OneToOne
	@JoinColumn
	private User user;
	
	@ManyToOne
	@JoinColumn
	private Presentation presentation;
	
}
