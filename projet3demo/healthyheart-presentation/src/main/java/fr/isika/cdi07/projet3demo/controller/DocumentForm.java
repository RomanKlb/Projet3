package fr.isika.cdi07.projet3demo.controller;

import org.springframework.web.multipart.MultipartFile;

import fr.isika.cdi07.projet3demo.model.Document;
import fr.isika.cdi07.projet3demo.model.Projet;

public class DocumentForm {
	
	private Document document;
	
	private Projet projet;
	
	private MultipartFile  image;
	
	private String libelImage;
	

	public String getLibelImage() {
		return libelImage;
	}

	public void setLibelImage(String libelImage) {
		this.libelImage = libelImage;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}

	
	
}
