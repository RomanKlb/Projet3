package fr.isika.cdi07.projet3demo.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.isika.cdi07.projet3demo.model.Utilisateur;
import fr.isika.cdi07.projet3demo.services.ProjetService;
import fr.isika.cdi07.projet3demo.services.UtilisateurService;

@Controller
public class DefaultController {
	
	@Autowired
	private UtilisateurService utilisateurService;
	
	private static final Logger LOGGER = Logger.getLogger(MessagerieController.class.getSimpleName());
	

	@GetMapping("/error")
	public String afficherMessageErreur(Model model,HttpSession session) {

		return "error";
	}
	
	@GetMapping("/")
	public String afficherPageAccueil(Model model,HttpSession session) {
		boolean isConnected = false;
		Utilisateur monUtilisateur = null;
		String userEmail = (String) session.getAttribute("emailUtilisateurConnecte");
		if (userEmail != null) {
			monUtilisateur = utilisateurService.chercherUtilisateurParEmail(userEmail);
			isConnected = true;
			LOGGER.info("Utilisateur connecte : " + monUtilisateur);
		}
		
		
		
		model.addAttribute("isConnected", isConnected);
		model.addAttribute("Utilisateur", monUtilisateur);
		

		return "index";
	}
	
	
	@GetMapping("/accueil")
	public String afficherPageAccueilDemo(Model model,HttpSession session) {
		boolean isConnected = false;
		Utilisateur monUtilisateur = null;
		String userEmail = (String) session.getAttribute("emailUtilisateurConnecte");
		if (userEmail != null) {
			monUtilisateur = utilisateurService.chercherUtilisateurParEmail(userEmail);
			isConnected = true;
			LOGGER.info("Utilisateur connecte : " + monUtilisateur);
		}
		
		
		
		model.addAttribute("isConnected", isConnected);
		model.addAttribute("Utilisateur", monUtilisateur);
		

		return "indexold";
	}
}
	