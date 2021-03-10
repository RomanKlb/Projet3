package fr.isika.cdi07.projet3demo.controller;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import fr.isika.cdi07.projet3demo.model.Document;
import fr.isika.cdi07.projet3demo.model.Projet;
import fr.isika.cdi07.projet3demo.model.TypeDocument;
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

	@GetMapping("/NewPictureForm/{id}")
	public String NewPictureForm(@PathVariable(value = "id") Long id, Model model,HttpSession session) {

		// LOGGER.info("Projet id en entrée : " + id);
		Optional<Projet> projet = projetService.getProjetById(id);
		Optional<Object> errorMsg = Optional.ofNullable(session.getAttribute("ErrorNewPicture"));
		String msgerr = "";
		if (errorMsg.isPresent()) {
			msgerr = errorMsg.get().toString();
			session.removeAttribute("ErrorNewPicture");
		}
		// LOGGER.info("Projet en entrée : " + projet.get());

		DocumentForm documentForm = new DocumentForm();
		Document document = new Document();
		document.setProjet(projet.get());
		document.setTypeDocument(TypeDocument.JPG);
		documentForm.setDocument(document);
		documentForm.setProjet(projet.get());
		List<TypeLibelleDoc> listeDoc = documentService.afficherAllLibelleImage();
		
		model.addAttribute("listLibelleImage",listeDoc);
		model.addAttribute("docform", documentForm);
		model.addAttribute("msgerr", msgerr);

		List<Document> ListeImagesProjet = documentService.afficherListeImageDuProjet(projet);
		model.addAttribute("listImageProjet", ListeImagesProjet);
		return "newPictureProjet";
	}

	
	@PostMapping("/NewUploadPicture")
	public String newSavePicture(@ModelAttribute("docform") DocumentForm documentForm,HttpSession session) throws IOException {
		Projet monProjet = documentForm.getProjet();
		LOGGER.info("Set idProjet sur NewSave in document : " + monProjet.getIdProjet() + 
				" /" + documentForm.getDocument() + " / " + documentForm.getImage().getOriginalFilename() +
				"/ size : " + documentForm.getImage().getSize()); 
		
		
		TypeLibelleDoc libelDoc = null;
		
		switch (documentForm.getLibelImage()) {
			case "1" : libelDoc = TypeLibelleDoc.IMAGE_PRINCIPALE;break;
			case "2" : libelDoc = TypeLibelleDoc.IMAGE_SECONDE;break;
			case "3" : libelDoc = TypeLibelleDoc.IMAGE_TROISIEME;break;
			default : session.setAttribute("ErrorNewPicture", "Vous n'avez n'avez pas selectionné de type d'image");
					return "redirect:/NewPictureForm/" + monProjet.getIdProjet();
				
		}
		if(documentForm.getImage().getSize() == 0) {
			session.setAttribute("ErrorNewPicture", "Vous n'avez pas selectionné d'image");
			return "redirect:/NewPictureForm/" + monProjet.getIdProjet();
		}
		
		Optional<TypeLibelleDoc> opt = documentService.findbyProjetAndLibelle(monProjet, libelDoc);
		if(opt.isPresent()) {
			session.setAttribute("ErrorNewPicture", "Il y a déjà un ce type d'image ("+
					libelDoc + ") pour le projet");
			
			return "redirect:/NewPictureForm/" + monProjet.getIdProjet();
		}
		
		documentForm.getDocument().setLibelle(libelDoc);
		documentForm.getDocument().setDate(Date.from(Instant.now()));
		documentForm.getDocument().setProjet(monProjet);
		
		try {
			documentForm.getDocument().setFichier(documentForm.getImage().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		documentService.saveImage(documentForm.getDocument());
		LOGGER.info("Document créé : " + documentForm.getDocument());
		return "redirect:/NewPictureForm/" + monProjet.getIdProjet();
	}
	
	
	@RequestMapping(value = "/viewImage/{id}", method = RequestMethod.GET)
	public void getImageAsByteArray(@PathVariable(value = "id") Long id,HttpServletResponse response) throws IOException {
		InputStream in = new ByteArrayInputStream(documentService.getDocumentById(id).getFichier());
	    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	    IOUtils.copy(in, response.getOutputStream());
	}
	
	
	@RequestMapping("/DelUploadPicture/{id}")
	public String DelPicture(@PathVariable(value = "id") Long id) {
		// LOGGER.info("Set idImage a deleter in document : " + id);
		
		Document monDocument = documentService.getDocumentById(id);
		Long idProjet = monDocument.getProjet().getIdProjet();
		
		documentService.DeleteDocument(monDocument);
		return "redirect:/NewPictureForm/" + idProjet;
	}
	


}
