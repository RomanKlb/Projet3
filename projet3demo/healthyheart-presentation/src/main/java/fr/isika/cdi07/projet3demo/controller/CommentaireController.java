package fr.isika.cdi07.projet3demo.controller;

import java.util.Optional;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.isika.cdi07.projet3demo.model.Commentaire;
import fr.isika.cdi07.projet3demo.model.Projet;
import fr.isika.cdi07.projet3demo.modelform.CommentaireForm;
import fr.isika.cdi07.projet3demo.services.ICommentaireService;
import fr.isika.cdi07.projet3demo.services.ProjetService;

@Controller
public class CommentaireController {
	
	private static final Logger logger = Logger.getLogger(DonController.class.getSimpleName());
	
	@Autowired
	private ICommentaireService commentaireService;
	
	@Autowired
	private ProjetService projetService;
	
	@GetMapping("/afficherListeCommentaires/projet")
	public String afficherListeCommentaires(@RequestParam long id, Model model, HttpSession session) {		
		String userEmail = (String)session.getAttribute("emailUtilisateurConnecte");
				
		CommentaireForm commentaireForm = new CommentaireForm(id);
		Optional<Projet> projet = projetService.getProjetById(id);
		if(!projet.isPresent())
			return "noFoundProjet";
		commentaireForm.setAllowedToComment(commentaireService.hasRoleToComment(projet.get(), userEmail));
		if(commentaireForm.isAllowedToComment())
			commentaireForm.setRole(commentaireService.getRoleNonLambda(userEmail).get());
		model.addAttribute("listeComm", commentaireService.getCommentairesList(projet.get()));
		model.addAttribute("commentaireForm", commentaireForm);
		return "liste_commentaires";
	}
	
	@PostMapping("/ajouterCommentaire")
	public String ajouterCommentaire(
			@ModelAttribute("commentaireForm") CommentaireForm commentaireForm) {
		Optional<Projet> projet = projetService.getProjetById(commentaireForm.getIdProjet());
		logger.info("Role commentaireForm : " + commentaireForm.getRole());
		Commentaire newCommentaire = new Commentaire()
				.withLibelle(commentaireForm.getLibelle())
				.withMessage(commentaireForm.getMessage())
				.withRole(commentaireForm.getRole())
				.withProjet(projet.get());
		commentaireService.saveCommentaire(newCommentaire);
		return "redirect:/afficherListeCommentaires/projet?id=" + commentaireForm.getIdProjet();
	}

}
