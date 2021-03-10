package fr.isika.cdi07.projet3demo.controller;

import fr.isika.cdi07.projet3demo.model.Territoire;
import fr.isika.cdi07.projet3demo.model.TypeProjet;

public class RechercheMulticriteresForm {
	
	private String titre;
	
	private TypeProjet typeProjet;
	
	private Territoire territoire;

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public TypeProjet getTypeProjet() {
		return typeProjet;
	}

	public void setTypeProjet(TypeProjet typeProjet) {
		this.typeProjet = typeProjet;
	}

	public Territoire getTerritoire() {
		return territoire;
	}

	public void setTerritoire(Territoire territoire) {
		this.territoire = territoire;
	}

	

	
	
	

}
