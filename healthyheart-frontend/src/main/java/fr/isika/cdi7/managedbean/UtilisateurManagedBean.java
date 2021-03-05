package fr.isika.cdi7.managedbean;

import java.time.Instant;
import java.util.Date;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.isika.cdi07.projet3demo.model.Utilisateur;
import fr.isika.cdi07.projet3demo.repository.DaoUtilisateur;

@ManagedBean(name="utilisateurMb")
@RequestScoped
public class UtilisateurManagedBean {

	private Utilisateur utilisateur;
	private String message;
	private DaoUtilisateur daoUtilisateur;

	


	public UtilisateurManagedBean() {
		utilisateur = new Utilisateur();
		daoUtilisateur = new DaoUtilisateur();
	}


	public String enregistrerNouvelUtilisateur() 
	{
		utilisateur.setDateMaj(Date.from(Instant.now()));

		daoUtilisateur.persisterUtilisateur(utilisateur);

		message = "Votre compte " + utilisateur.getEmail() +" est activé :)";
		return "connexion";
	}

	


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}


}
