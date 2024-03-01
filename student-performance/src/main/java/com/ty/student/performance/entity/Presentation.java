package com.ty.student.performance.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Presentation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "pre_id_gen")
	@SequenceGenerator(name = "pre_id_gen",initialValue = 201,allocationSize = 1)
	private int presentationId;
	private String topic;
	private int trainerId;
	private double trainerMark;
	
	@CreationTimestamp
	private LocalDateTime presentedDateTime;
	
	@ManyToOne
	@JoinColumn(name = "studentId")
	@Schema(hidden = true)
	private User user;
	
	@JsonIgnore
	@OneToMany(mappedBy = "presentation", cascade = CascadeType.ALL)
	private List<Voting> votings = new ArrayList<>();
	
}
