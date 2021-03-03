package fr.isika.cdi7.managedbean;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.isika.cdi07.projet3demo.model.Projet;
import fr.isika.cdi07.projet3demo.model.StatutProjet;
import fr.isika.cdi07.projet3demo.repository.DaoProjet;

@ManagedBean(name = "projetMb")
@RequestScoped
public class ProjetManagedBean {

	private DaoProjet daoProjet;

	private Projet projet;
	private String message;
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

	public List<Projet> getProjets() {
		return projets;
	}

	public void setProjets(List<Projet> projets) {
		this.projets = projets;
	}


	







}
