package fr.isika.cdi07.projet3demo.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.isika.cdi07.projet3demo.model.Utilisateur;
import fr.isika.cdi07.projet3demo.services.UtilisateurService;

public class DefaultController {
	
	private static final Logger LOGGER = Logger.getLogger(MessagerieController.class.getSimpleName());
	
	@Autowired
	private UtilisateurService utilisateurService;

	@GetMapping("/error")
	public String afficherMessageErreur(Model model,HttpSession session) {

		return "error";
	}
	
	
	@GetMapping("/")
	public String afficherPageAccueil(Model model,HttpSession session) {
		boolean isConnected = false;
		Utilisateur monUtilisateur = null;
		Long nbMsg = -1L;
		String userEmail = (String) session.getAttribute("emailUtilisateurConnecte");
		if (userEmail != null) {
			monUtilisateur = utilisateurService.chercherUtilisateurParEmail(userEmail);
			isConnected = true;
		}
		
		model.addAttribute("isConnected", isConnected);
		model.addAttribute("nbMsg", nbMsg);
		model.addAttribute("Utilisateur", monUtilisateur);
		
		return "index";
	}
	
}
	