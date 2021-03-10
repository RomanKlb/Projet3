package fr.isika.cdi07.projet3demo.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import fr.isika.cdi07.projet3demo.dao.DocumentRepository;
import fr.isika.cdi07.projet3demo.model.Document;
import fr.isika.cdi07.projet3demo.model.Projet;
import fr.isika.cdi07.projet3demo.model.TypeDocument;
import fr.isika.cdi07.projet3demo.model.TypeLibelleDoc;

@Service
public class DocumentService {
	
	@Autowired
	private DocumentRepository documentRepo;

	public Document saveImage(Document document) {
		
		
		document.setDate(Date.from(Instant.now()));
		document.setTypeDocument(TypeDocument.JPEG);
		
		return documentRepo.save(document);
		
	}

	public List<TypeLibelleDoc> afficherAllLibelleImage() {
		List<TypeLibelleDoc> libelleImages = new ArrayList<TypeLibelleDoc>();
			libelleImages.add(TypeLibelleDoc.IMAGE_PRINCIPALE);
			libelleImages.add(TypeLibelleDoc.IMAGE_SECONDE);
			libelleImages.add(TypeLibelleDoc.IMAGE_TROISIEME);
			
		return libelleImages;
	}
	
	public Document getDocumentById(Long id) {
		Optional<Document> optionalD = documentRepo.findById(id);
		Document document = null;
		if(optionalD.isPresent()) {
			document = optionalD.get();
		}
		return document;
	}

	public List<Document> listImage(byte[] fichier) {
		
		
		return null;	
	}

	public List<Document> afficherListeImageDuProjet(Optional<Projet> projet) {
		return documentRepo.findAllByProjet(projet);
	}

	public Optional<TypeLibelleDoc> findbyProjetAndLibelle(Projet projet, TypeLibelleDoc libelle) {
		return documentRepo.findByProjetAndLibelle(projet, libelle);
		
	}
	
	public void DeleteDocument(Document document) {
		documentRepo.delete(document);
	}
	

}
