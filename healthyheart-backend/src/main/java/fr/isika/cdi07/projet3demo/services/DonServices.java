package fr.isika.cdi07.projet3demo.services;

import fr.isika.cdi07.projet3demo.model.DonMateriel;
import fr.isika.cdi07.projet3demo.model.DonMonetaire;
import fr.isika.cdi07.projet3demo.model.DonTemps;
import fr.isika.cdi07.projet3demo.model.StatutDon;

public class DonServices {

	public void affecterStatutDonMonetaire(DonMonetaire don) {
		if(don.getMontant() >= 2000) {
			don.getParticipationProjet().setStatutDon(StatutDon.EN_ATTENTE);
		}			
		else
			don.getParticipationProjet().setStatutDon(StatutDon.APPROUVE);
			
	}
	
	public void affecterStatutDonTemps(DonTemps don) {
		if(don.getNbHeures() >= 100) {
			don.getParticipationProjet().setStatutDon(StatutDon.EN_ATTENTE);
		}			
		else
			don.getParticipationProjet().setStatutDon(StatutDon.APPROUVE);			
	}
	
	public void affecterStatutDonMateriel(DonMateriel don) {
		if(don.getMontant() >= 2000) {
			don.getParticipationProjet().setStatutDon(StatutDon.EN_ATTENTE);
		}			
		else
			don.getParticipationProjet().setStatutDon(StatutDon.APPROUVE);			
	}
	

	
}
