package com.mkkim.myspringboot.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mkkim.myspringboot.entity.User;
import com.mkkim.myspringboot.exception.ResourceNotFoundException;
import com.mkkim.myspringboot.repository.UserRepository;

@Controller
public class UserController {
	@Autowired
	UserRepository repository;
	
	@GetMapping("/index")
	public ModelAndView index(Model model) {
		return new ModelAndView("index", "users", repository.findAll());
	}
	
	@GetMapping("/adduser")
	public String showSignUpForm(User user) {
		return "add-user";
	}
	
	@PostMapping("/adduser")
	public String addUser(@Valid User user, BindingResult result, Model model, HttpServletResponse response) throws Exception{
		if (result.hasErrors()) {
			return "add-user";
		}
		repository.save(user);
		model.addAttribute("users", repository.findAll());
		response.sendRedirect("/index");
		return "index";
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		Optional<User> optional = repository.findById(id);
		if (optional.isPresent()) {
			model.addAttribute("user", optional.get());
			return "edit-user";
		}
		return "index";
	}
	
	@PostMapping("/edit/{id}")
	public String updateUser(@PathVariable("id") Long id, @Valid User user) {
		Optional<User> optional = repository.findById(id);
		if (optional.isPresent()) {
			repository.save(user);
		}
		return "index";
	}
	
	@PostMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") Long id, Model model) {
		User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
		repository.delete(user);
		model.addAttribute("users", repository.findAll());
		return "index";
	}
	
	@GetMapping("/mypage")
	public String mypage(Model model) {
		String username = null;
		model.addAttribute("username", username);
		return "mypage";
	}
}
