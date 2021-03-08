package fr.isika.cdi07.projet3demo.modelform;

import fr.isika.cdi07.projet3demo.model.DonMateriel;
import fr.isika.cdi07.projet3demo.model.DonMonetaire;
import fr.isika.cdi07.projet3demo.model.DonTemps;
import fr.isika.cdi07.projet3demo.model.ParticipationProjet;

public class DonForm {
	
	private DonMonetaire donMonetaire;
	private DonTemps donTemps;
	private DonMateriel donMateriel;
	private ParticipationProjet participationProjet;
	private boolean anonyme;

	public DonMonetaire getDonMonetaire() {
		return donMonetaire;
	}

	public void setDonMonetaire(DonMonetaire donMonetaire) {
		this.donMonetaire = donMonetaire;
	}

	public DonTemps getDonTemps() {
		return donTemps;
	}

	public void setDonTemps(DonTemps donTemps) {
		this.donTemps = donTemps;
	}

	public DonMateriel getDonMateriel() {
		return donMateriel;
	}

	public void setDonMateriel(DonMateriel donMateriel) {
		this.donMateriel = donMateriel;
	}

	public ParticipationProjet getParticipationProjet() {
		return participationProjet;
	}

	public void setParticipationProjet(ParticipationProjet participationProjet) {
		this.participationProjet = participationProjet;
	}

	public boolean isAnonyme() {
		return anonyme;
	}

	public void setAnonyme(boolean anonyme) {
		this.anonyme = anonyme;
	}
	
	
}
