package fr.isika.cdi07.projet3demo.controller;

import fr.isika.cdi07.projet3demo.model.Categorie;
import fr.isika.cdi07.projet3demo.model.PorteurProjet;
import fr.isika.cdi07.projet3demo.model.Projet;
import fr.isika.cdi07.projet3demo.model.Role;
import fr.isika.cdi07.projet3demo.model.Territoire;
import fr.isika.cdi07.projet3demo.model.TypeProjet;

public class ProjetForm {

	private Projet projet;
	private PorteurProjet porteurProjet;
	private Categorie categorie;
	private Territoire territoire;
	private TypeProjet typeProjet;
	private Role role;
	
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

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

	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	public Territoire getTerritoire() {
		return territoire;
	}
	public void setTerritoire(Territoire territoire) {
		this.territoire = territoire;
	}
	public TypeProjet getTypeProjet() {
		return typeProjet;
	}
	public void setTypeProjet(TypeProjet typeProjet) {
		this.typeProjet = typeProjet;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProjetForm [projet=");
		builder.append(projet);
		builder.append(", porteurProjet=");
		builder.append(porteurProjet);
		builder.append(", categorie=");
		builder.append(categorie);
		builder.append(", territoire=");
		builder.append(territoire);
		builder.append(", typeProjet=");
		builder.append(typeProjet);
		builder.append(", role=");
		builder.append(role);
		builder.append("]");
		return builder.toString();
	}
	
}
