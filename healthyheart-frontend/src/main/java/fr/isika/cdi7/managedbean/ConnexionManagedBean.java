package fr.isika.cdi7.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.isika.cdi07.projet3demo.model.Utilisateur;
import fr.isika.cdi07.projet3demo.repository.DaoUtilisateur;

@ManagedBean(name="connexionMb")
@SessionScoped
public class ConnexionManagedBean {

	private Utilisateur utilisateurConnecte;
	private String email;
	private String mdp;
	private DaoUtilisateur daoUtilisateur;
	
	public ConnexionManagedBean() {
		daoUtilisateur = new DaoUtilisateur();
	}

	public String seConnecter() {

		utilisateurConnecte = daoUtilisateur.rechercherUtilisateurParLoginMdp(email, mdp);

		return "connexion";
	}
	
	public String seDeconnecter() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "creerCompte";
	}

	public Utilisateur getUtilisateurConnecte() {
		return utilisateurConnecte;
	}

	public void setUtilisateurConnecte(Utilisateur utilisateurConnecte) {
		this.utilisateurConnecte = utilisateurConnecte;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	
	
}
