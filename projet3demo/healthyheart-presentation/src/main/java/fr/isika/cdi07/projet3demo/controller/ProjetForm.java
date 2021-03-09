package fr.isika.cdi07.projet3demo.controller;

import fr.isika.cdi07.projet3demo.model.PorteurProjet;
import fr.isika.cdi07.projet3demo.model.Projet;

public class ProjetForm {

	private Projet projet;
	private PorteurProjet porteurProjet;
	
	
	public Projet getProjet() {
		return projet;
	}
	public void setProjet(Projet projet) {
		this.projet = projet;
	}
	public PorteurProjet getPorteurProjet() {
		return porteurProjet;
	}
	public void setPorteurProjet(PorteurProjet porteurProjet) {
		this.porteurProjet = porteurProjet;
	}
	
	
	
}
