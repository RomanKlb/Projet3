package fr.isika.cdi7.managedbean;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import fr.isika.cdi07.projet3demo.model.Projet;
import fr.isika.cdi07.projet3demo.model.StatutProjet;
import fr.isika.cdi07.projet3demo.repository.DaoProjet;

@ManagedBean(name = "projetMb")
@RequestScoped
public class ProjetManagedBean {

	private DaoProjet daoProjet;

	private Projet projet;
	private String message;
	private List<Projet> listeProjet;
	
	public ProjetManagedBean() {
		projet = new Projet();
		daoProjet = new DaoProjet();
		
	}

	public String ajouterProjet() {
		projet.setDateMaj(Date.valueOf(LocalDate.now()));
		projet.setMontantCollecte(Double.valueOf(0));
		projet.setStatutDuProjet(StatutProjet.CREE);
		daoProjet.persister(projet);
		
		message = "Le projet "+projet.getTitre() + " a bien été ajouté";
		
		return "";
	}
	
	public String rechercherProjetParTitre() {
		
		listeProjet = daoProjet.rechercheProjetParTitre(projet.getTitre());
		
		return "";	
	}
	
	public String modifierProjet() {
		
		daoProjet.modifierProjet(projet);
		
		message = "Le projet " + projet.getTitre() + " a bien été modifié";
		
		return "";
	}
	
	public String afficherLesDonneesDuProjetPourModification() {
		
		daoProjet.rechercheProjetParId(id);
		
		return "/modifierProjet.xhtml?faces-redirect=true";
		
	}
	
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}


	public void setListeProjet(List<Projet> listeProjet) {
		this.listeProjet = listeProjet;
	}

	public List<Projet> getListeProjet() {
		return listeProjet;
	}


	

	
	
	
}
