package com.app.firstclient.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecurityController {
	
	@GetMapping("/username")
	@ResponseBody
	public String getCurrentUser(Authentication authentication) {
		System.out.println("User Authentication : " + authentication);
		return authentication.getName();
	}
}
