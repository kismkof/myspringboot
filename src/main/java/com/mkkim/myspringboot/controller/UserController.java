package com.mkkim.myspringboot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mkkim.myspringboot.entity.User;
import com.mkkim.myspringboot.repository.UserRepository;

@Controller
public class UserController {
	@Autowired
	UserRepository repository;
	
	@GetMapping("/signup")
	public String showSignUpForm(User user) {
		return "add-user";
	}
	
	@PostMapping("adduser")
	public String addUser(@Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-user";
		}
		
		repository.save(user);
		model.addAttribute("users", repository.findAll());
		return "index";
	}
	
	@GetMapping("/index")
	public ModelAndView index(Model model) {
		return new ModelAndView("index", "users", repository.findAll());
	}
}
