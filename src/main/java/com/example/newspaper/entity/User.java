package com.example.newspaper.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
	
	@Id
	private String username;
	private String password;
	private String name;
	private String role;
	
	

}
