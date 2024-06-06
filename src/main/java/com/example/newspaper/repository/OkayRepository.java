package com.example.newspaper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.newspaper.entity.Okay;

public interface OkayRepository extends JpaRepository<Okay, Long>{
	
	@Query("SELECT o FROM Okay o JOIN o.ano a WHERE a.approved = true")
	public List<Okay> findAllokayList();

}
