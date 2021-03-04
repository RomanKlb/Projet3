package fr.isika.cdi7.managedbean;

<<<<<<< HEAD
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
=======
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
>>>>>>> 0d20fee00afe1dc275d9756a6e59d1bb81b6cdce

import fr.isika.cdi07.projet3demo.model.Projet;
import fr.isika.cdi07.projet3demo.model.StatutProjet;
import fr.isika.cdi07.projet3demo.repository.DaoProjet;

@ManagedBean(name = "projetMb")
@RequestScoped
public class ProjetManagedBean {

	private DaoProjet daoProjet;

	private Projet projet;
	private String message;
<<<<<<< HEAD
	private List<Projet> projets;

	public ProjetManagedBean() {
		projet = new Projet();
		daoProjet = new DaoProjet();

	}

	public String ajouterProjet() {
		projet.setDateMaj(Date.from(Instant.now()));
		projet.setMontantCollecte(Double.valueOf(0));
		projet.setStatutDuProjet(StatutProjet.CREE);
		daoProjet.persisterProjet(projet);

		message = "Le projet "+projet.getTitre() + " a bien été ajouté";

		return "";
	}

	public String rechercherProjetParTitre() {

		projets = daoProjet.rechercheProjetParTitre(projet.getTitre());

		return "";	
	}
	
	public String rechercherTousLesProjetsParStatutPublie() {
		
		projets = daoProjet.obtenirTousLesProjetsParStatutPublie();
		
		
		return "";
		
	}

	public String modifierProjet() {

		daoProjet.modifierProjet(projet);

		message = "Le projet " + projet.getTitre() + " a bien été modifié";

		return "";
	}

	public String recuperationTitre(){

		return "modifierProjet";
	}


=======
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
	
	
	
>>>>>>> 0d20fee00afe1dc275d9756a6e59d1bb81b6cdce
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

<<<<<<< HEAD
	public List<Projet> getProjets() {
		return projets;
	}

	public void setProjets(List<Projet> projets) {
		this.projets = projets;
=======

	public void setListeProjet(List<Projet> listeProjet) {
		this.listeProjet = listeProjet;
	}

	public List<Projet> getListeProjet() {
		return listeProjet;
>>>>>>> 0d20fee00afe1dc275d9756a6e59d1bb81b6cdce
	}


	

<<<<<<< HEAD






=======
	
	
	
>>>>>>> 0d20fee00afe1dc275d9756a6e59d1bb81b6cdce
}
