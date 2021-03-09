package fr.isika.cdi07.projet3demo.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class DefaultController {
	
	private static final Logger LOGGER = Logger.getLogger(MessagerieController.class.getSimpleName());
	

	@GetMapping("/error")
	public String afficherMessageErreur(Model model,HttpSession session) {

		return "error";
	}
	
	@GetMapping("/index")
	public String afficherPageAccueil(Model model,HttpSession session) {

		return "index";
	}
}
	