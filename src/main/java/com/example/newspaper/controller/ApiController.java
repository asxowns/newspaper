package com.example.newspaper.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.newspaper.entity.Article;
import com.example.newspaper.entity.Okay;
import com.example.newspaper.entity.User;
import com.example.newspaper.repository.ArticleRepository;
import com.example.newspaper.repository.OkayRepository;
import com.example.newspaper.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ArticleRepository articleRepo;
	
	@Autowired
	OkayRepository okayRepo;
	
	@PostMapping("/regist")
	public ResponseEntity<String> regist(@RequestBody User user) {
		
		user.setRole("reporter");
		userRepo.save(user);
		
		return ResponseEntity.ok("regist success");
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpServletRequest request) {
		
		HttpSession session = request.getSession();

		session.invalidate();
		
		return ResponseEntity.ok("logout success");
	}
	
	@PostMapping("/write")
	public ResponseEntity<String> write(@RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam("writer") String writerUsername) {
		
		User writer = userRepo.findByUsername(writerUsername);
		if(writer == null) {
			throw new IllegalArgumentException("User not found: " + writerUsername);
		}
		
		Article article = new Article();
		article.setTitle(title);
		article.setContent(content);
		article.setWriter(writer);
		
		articleRepo.save(article);
		
		return ResponseEntity.ok("write success");
	}
	
	@GetMapping("/articleList")
	public List<Article> articleList(Article article) {
		
		List<Article> list = articleRepo.findAll();
		
		return list;
	}
	
	@PostMapping("/okay")
	public Okay okay(@RequestBody Map<String, Long> requestBody) {

		Long anoId = requestBody.get("ano");
        Article article = articleRepo.findById(anoId)
                .orElseThrow(() -> new RuntimeException("Article not found"));
        
        article.setApproved(true);
        articleRepo.save(article);

        Okay okay = new Okay();
        okay.setAno(article);
		
		okayRepo.save(okay);
		
		return okay;
	}
	
	@GetMapping("/okayList")
	public List<Okay> okayList() {
		
		List<Okay> list = okayRepo.findAllokayList();
		
		return list;
	}
	
	

}
