package com.example.newspaper.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Okay {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ono;
	
	private LocalDate date = LocalDate.now();
	
	@ManyToOne
	@JoinColumn(name = "ano", referencedColumnName = "ano")
	private Article ano;

}
