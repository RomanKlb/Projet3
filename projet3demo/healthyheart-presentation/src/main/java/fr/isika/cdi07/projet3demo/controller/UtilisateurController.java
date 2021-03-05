package fr.isika.cdi07.projet3demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.isika.cdi07.projet3demo.model.Projet;
import org.springframework.web.bind.annotation.PostMapping;

import fr.isika.cdi07.projet3demo.model.Utilisateur;
import fr.isika.cdi07.projet3demo.services.UtilisateurService;

@Controller
public class UtilisateurController {

	@Autowired
	private UtilisateurService utilisateurService;
	
	//Créer Nouvel Utilisateur
	@GetMapping("/CreerNouvelUtilsateur")
	public String creerNouvelUtilisateur(Model model) {
		Utilisateur utilisateur = new Utilisateur();
		model.addAttribute("utilisateur", utilisateur);
		return "newUtilisateur";
	}

	//Enregistrer un utilisateur
	@PostMapping("/enregistrerUtilisateur")
	public String enregistrerNouvelUtilisateur(@ModelAttribute("utilisateur") Utilisateur utilisateur) {
		utilisateurService.ajouterUtilisateur(utilisateur);
		return "index";
	}

	//Pour modifier Un utilisateur 
	@GetMapping("/showUsersForUpdate/{email}")
	public String afficherLesUtilisateurPourModification(@PathVariable (value ="email") String email, Model model) {		
		Utilisateur utilisateur = utilisateurService.chercherOptionalUtilisateurParEmail(email);
		model.addAttribute("utilisateur", utilisateur);
		return "update_utilisateur";	
	}
	
	//Afficher Tous les utilisateurs
	@GetMapping("/showAllUsers")
	public String afficherTousUtilisateurs(Model model) {
		model.addAttribute("listeUtilisateurs", utilisateurService.retournerTousLesUtilisateur());
		return "listeUtilisateurs";
	}
	
	@GetMapping("/ConnecterUtilsateur")
	public String saisirInformationUtilisateur(Model model) {
		Utilisateur utilisateur = new Utilisateur();
		model.addAttribute("utilisateur", utilisateur);
		return "seConnecter";
	}
	
	//Essayer de connecter un utilisateur
	@GetMapping("/ConnecterUtilsateur/{email}")
	public String connecterUtilisateur(@PathVariable (value ="email") String email, Model model) {
		Utilisateur utilisateur = utilisateurService.chercherOptionalUtilisateurParEmail(email);
		model.addAttribute("utilisateur", utilisateur);
		return "utilisateurConnecte";
	}
	
	//@PostMapping("/SeConnecter")
	//public String connecterUtilisateur(@ModelAttribute("utilisateur") Utilisateur utilisateur) {
		//Utilisateur utilisateurToUse = null;
		//Utilisateur utilisateurToUse = utilisateurService.connecterUtilisateur(email, mdp);
		//utilisateurToUse.getEmail().equals(email);
		//utilisateurService.connecterUtilisateur(utilisateur.getEmail(), utilisateur.getMdp());
			
		//return "utilisateurConnecte";
	//}	
	
	
	@GetMapping("/ShowAllUtilisateur")
	public String showAllUtilisateur(Model model) {
		model.addAttribute("listUtilisateur", utilisateurService.afficherAllUtilisateur());
		return "listUtilisateur";
	}
	
	@GetMapping("/ShowNewUtilisateur")
	public String showNewUtilisateur(Model model) {
				
		//create model attribute to bind form data
		Utilisateur utilisateur = new Utilisateur();
		model.addAttribute("utilisateur", utilisateur);
		return "newUtilisateur";
	}
	
	@PostMapping("/saveUtilisateur")
	public String saveUtilisateur(@ModelAttribute("utilisateur") Utilisateur utilisateur) {
		//save projet to database
		utilisateurService.ajouterUtilisateur(utilisateur);
		return "listUtilisateur";
	}

}
