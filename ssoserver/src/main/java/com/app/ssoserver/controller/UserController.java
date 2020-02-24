package com.app.ssoserver.controller;

import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping("/user/me")
	public Principal user(Principal principal) {
		log.info(principal.toString());
		return principal;
	}
}
