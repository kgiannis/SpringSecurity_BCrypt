package com.rt.jdbc_bcrypt_pass.ctrl;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class RequestsCtrl {
	
	@GetMapping(value="/")
	public String index(Model model) {	
		model.addAttribute("show", true);
		return "index";
	}

	@GetMapping(value="/admin")
	public String adminIndex(Model model, Principal principal) {
		model.addAttribute("title", "Admin Index");
		model.addAttribute("body", "Welcome Admin...");
		model.addAttribute("role", "admin");
		return "index";
	}
	
	@GetMapping(value="/admin/path")
	public String adminPath(Model model, Principal principal) {
		model.addAttribute("title", "Admin Path");
		model.addAttribute("body", "Welcome Admin...");
		model.addAttribute("role", "admin");
		return "index";
	}
	
	@GetMapping(value="/users")
	public String usersIndex(Model model, Principal principal) {
		model.addAttribute("title", "Users Index");
		model.addAttribute("body", "Welcome User...");
		model.addAttribute("role", "user");
		return "index";
	}
	
	@GetMapping(value="/users/path")
	public String usersPath(Model model, Principal principal) {
		model.addAttribute("title", "Users Path");
		model.addAttribute("body", "Welcome User...");
		model.addAttribute("role", "user");
		return "index";
	}
	
	@GetMapping(value="/adus")
	public String adusIndex(Model model, Principal principal) {
		model.addAttribute("title", "Adus Index");
		model.addAttribute("body", "Welcome Adus...");
		model.addAttribute("role", "adus");
		return "index";
	}
	
	@GetMapping(value="/adus/path")
	public String adusPath(Model model, Principal principal) {
		model.addAttribute("title", "Adus Path");
		model.addAttribute("body", "Welcome Adus...");
		model.addAttribute("role", "adus");
		return "index";
	}
	
	
	
	/** ---------- LOGIN ---------- */
	
	@GetMapping(value="/login")
	public String login(Model model,Principal principal) {
		model.addAttribute("hide", principal==null?true:false);
		return "index";
	}
	
	
	
	/** ---------- LOGOUT ---------- */
	
	@GetMapping(value="/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if( auth != null ) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}
}
