package com.app.ssoserver.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

	@RequestMapping("/exit")
	public void exit(HttpServletRequest request, HttpServletResponse response) {
		new SecurityContextLogoutHandler().logout(request, null, null);
		try {
			response.sendRedirect(request.getHeader("referer"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
