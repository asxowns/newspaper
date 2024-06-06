package com.example.newspaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.newspaper.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>{
	
	public Article findByAno(@Param("ano") Long ano);
}
