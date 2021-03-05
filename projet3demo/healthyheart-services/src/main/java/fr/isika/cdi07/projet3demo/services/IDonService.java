package fr.isika.cdi07.projet3demo.services;

import java.util.List;

import fr.isika.cdi07.projet3demo.model.Don;
import fr.isika.cdi07.projet3demo.model.ParticipationProjet;

public interface IDonService<T extends Don> {

	List<T> afficherDons();
	Long compterDons();
	void enregistrerDansLaBase(T don, ParticipationProjet participationProjet);
	T obtenirDonById(long id);
	void supprimerDonById(long id);
}
