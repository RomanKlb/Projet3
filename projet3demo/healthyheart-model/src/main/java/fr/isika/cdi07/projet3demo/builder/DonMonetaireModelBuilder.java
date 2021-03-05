package fr.isika.cdi07.projet3demo.builder;

import java.sql.Date;
import java.time.LocalDate;

import fr.isika.cdi07.projet3demo.model.DonMonetaire;
import fr.isika.cdi07.projet3demo.model.ParticipationProjet;
import fr.isika.cdi07.projet3demo.model.TypeParticipation;

public class DonMonetaireModelBuilder implements IDonModelBuilder<DonMonetaire>{

	@Override
	public DonMonetaire build(DonMonetaire don) {
		ParticipationProjet participationProjet = new ParticipationProjet();
		participationProjet.setDate(Date.valueOf(LocalDate.now()));
		participationProjet.setTypeParticipation(TypeParticipation.MONETAIRE);
		
		don.withDate(Date.valueOf(LocalDate.now()))
			.withParticipationProjet(participationProjet);
		return null;
	}

}
