package fr.isika.cdi07.projet3demo.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.isika.cdi07.projet3demo.model.Document;
import fr.isika.cdi07.projet3demo.model.Projet;

import fr.isika.cdi07.projet3demo.services.DocumentService;
import fr.isika.cdi07.projet3demo.services.ProjetService;

@Controller
public class DocumentController {

	private static final Logger LOGGER = Logger.getLogger(DocumentController.class.getSimpleName());

	@Autowired
	private ProjetService projetService;

	@Autowired
	private DocumentService documentService;

	@GetMapping("/ShowNewPictureForm/{id}")
	public String showNewPictureForm(@PathVariable(value = "id") Long id, Model model) {

		// create model attribute to bind form data
		Projet projet = projetService.getProjetById(id);
		Document document = new Document();

		model.addAttribute("projet", projet);
		model.addAttribute("document", document);

		return "newPictureProjet";
	}

	@PostMapping("/uploadPicture")
	public String savePicture(@ModelAttribute("projet") Projet projet, 
			@ModelAttribute("document") Document document) {
		// save projet to database
//		LOGGER.info();

		documentService.saveImageJPG(document);
		return "redirect:/ShowAllProjetList";
	}

}
