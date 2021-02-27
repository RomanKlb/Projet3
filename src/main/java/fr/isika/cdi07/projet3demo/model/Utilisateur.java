package fr.isika.cdi07.projet3demo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Utilisateur {

	@Id
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String mdp;
	
	@Column(nullable = false)
	private String nom;
	
	@Column(nullable = false)
	private String prenom;
	
	@Column(nullable = false, name="date_maj")
	private LocalDateTime dateMaj;
	
	
	public Utilisateur() {
	}

	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Utilisateur [email=");
		builder.append(email);
		builder.append(", mdp=");
		builder.append(mdp);
		builder.append(", nom=");
		builder.append(nom);
		builder.append(", prenom=");
		builder.append(prenom);
		builder.append(", dateMaj=");
		builder.append(dateMaj);
		builder.append("]");
		return builder.toString();
	}



	public String getMdp() {
		return mdp;
	}


	public void setMdp(String mdp) {
		this.mdp = mdp;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public LocalDateTime getDateMaj() {
		return dateMaj;
	}


	public void setDateMaj(LocalDateTime dateMaj) {
		this.dateMaj = dateMaj;
	}


	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
