package fr.isika.cdi07.projet3demo.services.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import fr.isika.cdi07.projet3demo.dao.CommentaireRepository;
import fr.isika.cdi07.projet3demo.model.Commentaire;
import fr.isika.cdi07.projet3demo.services.CommentaireService;

@RunWith(MockitoJUnitRunner.class)
class CommentaireServiceTest {

	@Mock
	private CommentaireRepository commentaireRepo;
	
	@InjectMocks
	private CommentaireService commentaireService;
	
	@Test
	void shouldReturnCommentaireIfIsSaved() {
		Commentaire commentaire = new Commentaire();
		commentaire.setLibelle("test_commentaire");
		commentaire.setMessage("message du commentaire");	
		
		Mockito.when(commentaireRepo.save(Mockito.any(Commentaire.class)))
				.thenReturn(new Commentaire());
		
		Commentaire commentaireSaved = commentaireService.saveCommentaire(commentaire);
		
		assertEquals(commentaire.getLibelle(), commentaireSaved.getLibelle());
	}

}
