package fr.isika.cdi07.projet3demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class PortefeuilleProjet {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_portefeuille")
	private Long idPorteFeuille;
	
	@Column(nullable = false)
	private String libelle;
	
	@OneToMany
	private List<Projet> listeProjet;

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<Projet> getListeProjet() {
		return listeProjet;
	}

	public void setListeProjet(List<Projet> listeProjet) {
		this.listeProjet = listeProjet;
	}

	public Long getIdPorteFeuille() {
		return idPorteFeuille;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PortefeuilleProjet [idPorteFeuille=");
		builder.append(idPorteFeuille);
		builder.append(", libelle=");
		builder.append(libelle);
		builder.append(", listeProjet=");
		builder.append(listeProjet);
		builder.append("]");
		return builder.toString();
	}
	

		
	
	
}
