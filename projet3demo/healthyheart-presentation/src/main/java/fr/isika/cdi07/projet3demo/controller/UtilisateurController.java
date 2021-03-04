package fr.isika.cdi07.projet3demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.isika.cdi07.projet3demo.model.Projet;
import fr.isika.cdi07.projet3demo.model.Utilisateur;
import fr.isika.cdi07.projet3demo.services.UtilisateurService;

@Controller
public class UtilisateurController {

	@Autowired
	private UtilisateurService utilisateurService;
	
	@GetMapping("/CreerNouveauUtilsateur")
	public String creerNouveauUtilisateur(Model model) {
		Utilisateur utilisateur = new Utilisateur();
		model.addAttribute("utilisateur", utilisateur);
		return "newUtilisateur";
	}
	@PostMapping("/enregistrerUtilisateur")
	public String enregistrerNouveauUtilisateur(@ModelAttribute("utilisateur") Utilisateur utilisateur) {
		utilisateurService.ajouterUtilisateur(utilisateur);
		return "index";
	}
	
}
