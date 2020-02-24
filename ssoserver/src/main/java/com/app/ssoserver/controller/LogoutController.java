package com.app.ssoserver.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

	private static final Logger log = LoggerFactory.getLogger(LogoutController.class);
	
	@RequestMapping("/exit")
	public void exit(HttpServletRequest request, HttpServletResponse response) {
		new SecurityContextLogoutHandler().logout(request, null, null);
		try {
			response.sendRedirect(request.getHeader("referer"));
			log.info("Succes log out");
		} catch (Exception e) {
			log.warn(e.getMessage());
			e.printStackTrace();
		}
	}
}
