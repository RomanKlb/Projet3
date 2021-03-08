package fr.isika.cdi07.projet3demo.controller;


import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.isika.cdi07.projet3demo.model.Document;
import fr.isika.cdi07.projet3demo.model.Projet;
import fr.isika.cdi07.projet3demo.model.TypeLibelleDoc;
import fr.isika.cdi07.projet3demo.services.DocumentService;
import fr.isika.cdi07.projet3demo.services.ProjetService;

@Controller
public class DocumentController {

	private static final Logger LOGGER = Logger.getLogger(DocumentController.class.getSimpleName());

	@Autowired
	private ProjetService projetService;

	@Autowired
	private DocumentService documentService;


	@GetMapping("/showNewPictureForm/{id}")
	public String showNewPictureForm(@PathVariable(value = "id") Long id, Model model) {

		Optional<Projet> projet = projetService.getProjetById(id);

		Document document = new Document();
		List<TypeLibelleDoc> listeDoc = documentService.afficherAllLibelleImage();
		
		model.addAttribute("listLibelleImage",listeDoc);
		model.addAttribute("projet", projet);
		model.addAttribute("document", document);

		List<Document> afficherListeImageDuProjet = documentService.afficherListeImageDuProjet(projet);
		//		MultipartFile multipart = (MultipartFile) afficherListeImageDuProjet;
		List<String> convertedImages = afficherListeImageDuProjet
				.stream()
				.map(dcument -> Base64.getEncoder().encodeToString(dcument.getFichier()))
				.collect(Collectors.toList());
		model.addAttribute("listeDocumentsDuProjet", afficherListeImageDuProjet);
		model.addAttribute("listeDocumentsDuProjet", convertedImages);

		//		 model.addAttribute("pic",Base64.getEncoder().encodeToString());
		//		if(!documents.isEmpty())
		//		model.addAttribute("listImage", documentService.afficherImage());

		return "newPictureProjet";
	}

	@PostMapping("/uploadPicture")
	public String savePicture(@ModelAttribute("projet") Projet projet,
			@ModelAttribute("document") Document document) {
		LOGGER.info("Set idProjet in document : " + projet.getIdProjet());
		Optional<TypeLibelleDoc> opt = documentService.findbyProjetAndLibelle(projet, document.getLibelle());
		if(opt.isPresent())
			return "redirect:/showNewPictureForm/" + projet.getIdProjet();
		document.setProjet(projet);
		documentService.saveImage(document);
		return "redirect:/showNewPictureForm/" + projet.getIdProjet();

		/*
		 * SI déjà image principale alors return la meme page avec message warning 
		 * faire les si image principale secondaire troisieme ....
		 * faire la redirection sur la meme page le temps qu'il n'y a pas un autre bouton activé pour aller ailleurs
		 * faire l'insertion des images insérés en base sur cette page ... avec des getmapping en plus en récup les id 
		 * et les images déjà inséré.
		 */


		
	}


}
