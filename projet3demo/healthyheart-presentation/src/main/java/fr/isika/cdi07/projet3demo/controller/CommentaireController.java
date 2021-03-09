package fr.isika.cdi07.projet3demo.controller;

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
	
	@Autowired
	private ICommentaireService commentaireService;
	
	@Autowired
	private ProjetService projetService;
	
	@GetMapping("/afficherListeCommentaires/projet")
	public String afficherListeCommentaires(@RequestParam long id, Model model) {
		CommentaireForm commentaireForm = new CommentaireForm(id);
		Projet projet = projetService.getProjetById(id);
		model.addAttribute("listeComm", commentaireService.getCommentairesList(projet));
		model.addAttribute("commentaireForm", commentaireForm);
		return "liste_commentaires";
	}
	
	@PostMapping("/ajouterCommentaire")
	public String ajouterCommentaire(
			@ModelAttribute("commentaireForm") CommentaireForm commentaireForm,
			Model model) {
		Projet projet = projetService.getProjetById(commentaireForm.getIdProjet());
		Commentaire newCommentaire = new Commentaire()
				.withLibelle(commentaireForm.getLibelle())
				.withMessage(commentaireForm.getMessage())
				.withProjet(projet);
		commentaireService.saveCommentaire(newCommentaire);
		return "redirect:/afficherListeCommentaires/projet?id=" + commentaireForm.getIdProjet();
	}

}
