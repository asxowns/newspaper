package com.example.newspaper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.newspaper.entity.User;
import com.example.newspaper.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MyController {

	@Autowired
	UserRepository userRepo;

	@RequestMapping("/")
	public String root() {

		return "index";
	}

	@RequestMapping("/registForm")
	public String registForm() {

		return "registForm";
	}

	@RequestMapping("/loginForm")
	public String loginForm() {

		return "loginForm";
	}

	@PostMapping("/login")
	public String login(@RequestParam("username") String username, 
			@RequestParam("password") String password,
			HttpServletRequest request,
			Model model) {

		HttpSession session = request.getSession();

		User user = userRepo.findByUsernameAndPassword(username, password);

		if (user != null) {
			session.setAttribute("username", user.getUsername());
			session.setAttribute("name", user.getName());
			session.setAttribute("role", user.getRole());
			return "redirect:/";
		} else {
			model.addAttribute("msg", "로그인 실패! 다시 로그인해주세요.");
			return "loginForm";
		}
	}
	
	@GetMapping("/myPage")
	public String myPage() {
		
		return "myPage";
	}
	
	@GetMapping("/writeForm")
	public String writeForm() {
		
		return "writeForm";
	}
	
	@GetMapping("/articleList")
	public String articleList() {
		
		return "articleList";
	}
	
	@GetMapping("/editorPage")
	public String editorPage() {
		
		return "editorPage";
	}

}
