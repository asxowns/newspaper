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
public class Article {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ano;
	
	private String title;
	private String content;
	private LocalDate date = LocalDate.now();
	private boolean approved;
	
	@ManyToOne
	@JoinColumn(name= "writer", referencedColumnName = "username")
	private User writer;
	

}
