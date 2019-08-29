package com.mkkim.myspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mkkim.myspringboot.entity.User;
import com.mkkim.myspringboot.repository.UserRepository;

@Controller
public class LeafController {
	@Autowired
	UserRepository repository;
	
	@GetMapping("/leaf")
	public String leaf(Model model) {
		List<User> users = repository.findAll();
		
		if (users != null && users.size() > 0) {
			model.addAttribute("firstName", users.get(0).getName());
			model.addAttribute("email", users.get(0).getEmail());
		}
		model.addAttribute("lastName", "kim");
		return "leaf";
	}
}
