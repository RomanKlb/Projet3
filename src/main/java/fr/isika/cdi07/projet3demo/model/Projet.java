package fr.isika.cdi07.projet3demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@NamedQuery(name = "Projet.findAll", query="SELECT proj from Projet proj")
@NamedQuery(name = "Projet.findById", query="SELECT proj from Projet proj WHERE proj.id= :idProjetParam")


@Entity
public class Projet {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_projet")
	private Long idProjet;
	
	@Column(nullable = false)
	private String titre;
	
	@Column(nullable = false, name="description_courte")
	private String descriptionCourte;
	
	@Column(nullable = false, name="description_longue")
	private String descriptionLongue;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, name="date_maj")
	private Date dateMaj;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false, name="date_fin")
	private Date dateFin;
	
	@Column(nullable = false)
	private Double montantAttendu;
	
	@Column(nullable = false)
	private Double montantCollecte;
	
	@Column(name="don_materiel")
	private boolean donMateriel;
	@Column(name="date_temps")
	private boolean donTemps;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name="statut_du_projet")
	private StatutProjet statutDuProjet;
	

	public Projet() {
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public String getDescriptionCourte() {
		return descriptionCourte;
	}


	public void setDescriptionCourte(String descriptionCourte) {
		this.descriptionCourte = descriptionCourte;
	}


	public String getDescriptionLongue() {
		return descriptionLongue;
	}


	public void setDescriptionLongue(String descriptionLongue) {
		this.descriptionLongue = descriptionLongue;
	}


	public Date getDateMaj() {
		return dateMaj;
	}


	public void setDateMaj(Date dateMaj) {
		this.dateMaj = dateMaj;
	}


	public Date getDateFin() {
		return dateFin;
	}


	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}


	public Double getMontantAttendu() {
		return montantAttendu;
	}


	public void setMontantAttendu(Double montantAttendu) {
		this.montantAttendu = montantAttendu;
	}


	public Double getMontantCollecte() {
		return montantCollecte;
	}


	public void setMontantCollecte(Double montantCollecte) {
		this.montantCollecte = montantCollecte;
	}


	public boolean isDonMateriel() {
		return donMateriel;
	}


	public void setDonMateriel(boolean donMateriel) {
		this.donMateriel = donMateriel;
	}


	public boolean isDonTemps() {
		return donTemps;
	}


	public void setDonTemps(boolean donTemps) {
		this.donTemps = donTemps;
	}


	public StatutProjet getStatutDuProjet() {
		return statutDuProjet;
	}


	public void setStatutDuProjet(StatutProjet statutDuProjet) {
		this.statutDuProjet = statutDuProjet;
	}


	public Long getIdProjet() {
		return idProjet;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Projet [idProjet=");
		builder.append(idProjet);
		builder.append(", titre=");
		builder.append(titre);
		builder.append(", descriptionCourte=");
		builder.append(descriptionCourte);
		builder.append(", descriptionLongue=");
		builder.append(descriptionLongue);
		builder.append(", dateMaj=");
		builder.append(dateMaj);
		builder.append(", dateFin=");
		builder.append(dateFin);
		builder.append(", montantAttendu=");
		builder.append(montantAttendu);
		builder.append(", montantCollecte=");
		builder.append(montantCollecte);
		builder.append(", donMateriel=");
		builder.append(donMateriel);
		builder.append(", donTemps=");
		builder.append(donTemps);
		builder.append(", statutDuProjet=");
		builder.append(statutDuProjet);
		builder.append("]");
		return builder.toString();
	}




	
}
