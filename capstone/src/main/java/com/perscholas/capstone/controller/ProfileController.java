package com.perscholas.capstone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.perscholas.capstone.entity.User;
import com.perscholas.capstone.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProfileController {
	  @Autowired
	    private UserRepository userRepository;

	    @GetMapping("/profile")
	    public String showProfilePage(Authentication authentication, Model model) {
	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

	        // get user details
	        String name = userDetails.getUsername(); // Modified to getName()

	        List<String> roles = userDetails.getAuthorities()
	                .stream()
	                .map(GrantedAuthority::getAuthority)
	                .collect(Collectors.toList());

	        // get emmail and name
	        User user = userRepository.findByName(name); 
	        
	        if (user != null) {
	  
	        String email = user.getEmail();

	        
	        model.addAttribute("user", user);
	        model.addAttribute("name", name);
	        model.addAttribute("email", email);
	        model.addAttribute("roles", roles);
	        }

	        return "profile"; // details to profile
	    }
	}

