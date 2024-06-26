package com.security.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/")
	public String redirectToAccueil() {
		return "redirect:/accueil";
	}
	
	@GetMapping("/accueil")
	public String accueil(@AuthenticationPrincipal User user, Model model) {
		/*
		@AuthenticationPrincipal est une annotation utilisée dans Spring Security.
		Cette annotation permet d'accéder à l'utilisateur actuellement authentifié dans votre application,
		généralement dans les méthodes du contrôleur.
		 Elle simplifie le processus d'obtention des détails de l'utilisateur à partir du contexte de sécurité.
		 */
		model.addAttribute("user", user);
		return "index";
	}
	
	@GetMapping("/profile")
	public String profile(@AuthenticationPrincipal User user, Model model) {
		if(user != null) {
			model.addAttribute("user", user);
			return "administration/profile";
		} else {
			return "redirect:/accueil";
		}	
	}
	
	@GetMapping("/administration")
	public String administration2(@AuthenticationPrincipal User user, Model model) {
		if(user != null && user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			// retourne vrai si au moins 1 des éléménts du stream qui correspond au maoins à ROLE_ADMIN
			return "administration/administration";
		}else {
			return "administration/error";
		}
	}
	
	public String administration() {
		return "administration/administration";
	}
}
