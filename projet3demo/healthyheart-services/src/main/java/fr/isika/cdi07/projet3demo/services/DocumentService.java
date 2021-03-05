package fr.isika.cdi07.projet3demo.services;

import java.time.Instant;
import java.util.Date;

import org.springframework.stereotype.Service;

import fr.isika.cdi07.projet3demo.dao.DocumentRepository;
import fr.isika.cdi07.projet3demo.model.Document;
import fr.isika.cdi07.projet3demo.model.TypeDocument;
import fr.isika.cdi07.projet3demo.model.TypeLibelleDoc;

@Service
public class DocumentService {
	
	private DocumentRepository documentRepo;

	public Document saveImageJPEG(Document document) {
		
		document.setLibelle(TypeLibelleDoc.IMAGE_PRINCIPALE);
		document.setDate(Date.from(Instant.now()));
		document.setTypeDocument(TypeDocument.JPEG);
		
		return documentRepo.save(document);
		
	}
	
	public Document saveImageJPG(Document document) {
		
		document.setDate(Date.from(Instant.now()));
		document.setTypeDocument(TypeDocument.JPG);
		
		return documentRepo.save(document);
		
	}

}
