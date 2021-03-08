package fr.isika.cdi07.projet3demo.controller;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.isika.cdi07.projet3demo.model.MessageInterne;
import fr.isika.cdi07.projet3demo.model.MessageRecu;
import fr.isika.cdi07.projet3demo.model.Utilisateur;
import fr.isika.cdi07.projet3demo.services.MessageRecuService;
import fr.isika.cdi07.projet3demo.services.MessageService;
import fr.isika.cdi07.projet3demo.services.UtilisateurService;

@Controller
// @RequestMapping("/dons")
public class MessagerieController {
	
	private static final Logger LOGGER = Logger.getLogger(MessagerieController.class.getSimpleName());
	
	@Autowired 
	private MessageService messageService;
	
	@Autowired 
	private MessageRecuService messageRecu;
	
	@Autowired 
	private UtilisateurService utilisateurService;

	@GetMapping("/afficherListeMessages")
	public String afficherListeMessage(Model model) {
		model.addAttribute("count", messageService.compterMessages());
		model.addAttribute("messagesList", messageService.afficherMessages());
		return "listeMessages";
	}
	
	@GetMapping("/ajouterMessage")
	public String ajouterNouveauMessage(Model model) {
		MessagerieForm nouveauMessage = new MessagerieForm();
		model.addAttribute("messagerieForm", nouveauMessage);
		return "nouveau_message";
	}
	
	// @RequestMapping(value = "/sauvegarderMessage", method = RequestMethod.POST)
	@PostMapping(value = "/sauvegarderMessage")
	public String sauvegarderMessage(@ModelAttribute("messagerieForm") MessagerieForm messagerieForm,Model model) {
		// System.out.println("id don dans POST :" + don.getIdDon());
		
		// traitement ermetter
		String emetteur = messagerieForm.getMessage().getEmetteur().getEmail();
		Utilisateur tstUser = utilisateurService.chercherUtilisateurParEmail(emetteur);
		if (tstUser == null) {
			String errMsg = "L'emetteur avec l'email " + emetteur + " est inconnu dans notre référentiel";
			LOGGER.info(errMsg);
			model.addAttribute("msgerr", errMsg);
			return "nouveau_message";
		}
		
		// traitement des déstinataires


		System.out.println("infos listDestinataire :" + messagerieForm.getListeDest());
		String[] tabDest = messagerieForm.getListeDest().replace(',', ';').trim().split(";");
		
		if (tabDest.length == 0) {
			String errMsg = "Il n'y aucun destinataire saisi";
			LOGGER.info(errMsg);
			model.addAttribute("msgerr", errMsg);
			return "nouveau_message";
		}
		
		HashSet<Utilisateur> lstUserDest = new HashSet<Utilisateur>();
		
		for (String monDest : tabDest) {
			String email = monDest.trim();
			Utilisateur tstDest = utilisateurService.chercherUtilisateurParEmail(email);
			if (tstDest == null) {
				String errMsg = "Le destinataire avec l'email " + email + " est inconnu dans notre référentiel";
				LOGGER.info(errMsg);
				model.addAttribute("msgerr", errMsg);
				return "nouveau_message";
			}
			if (!lstUserDest.add(tstDest)) {
				String errMsg = "Le destinataire avec l'email " + email + " est en doubel dans les destinataire";
				LOGGER.info(errMsg);
				model.addAttribute("msgerr", errMsg);
				return "nouveau_message";
			}
		}

		
		// insert message
		Date dateMsg = Date.from(Instant.now());
		messagerieForm.getMessage().setDate(dateMsg);
		messageService.ajout(messagerieForm.getMessage());
		
		for (Utilisateur userDest : lstUserDest)
		{
			MessageRecu monDest = new MessageRecu();
			monDest.setDateHeure(dateMsg);
			monDest.setMessageInterne(messagerieForm.getMessage());
			monDest.setisRead(false);
			monDest.setUtilisateur(userDest);
			
			messageRecu.ajout(monDest);
		}
			
		return "redirect:/afficherListeMessages";
	}
	
	@GetMapping("/VisualierMessage/{idMsg}")
	public String VisualierMessage(@PathVariable String idMsg, Model model) {
		// System.out.println("id Msg dans VisualierMessage :" + idMsg);
		MessageInterne message = messageService.getMessageById(Long.valueOf(idMsg));
		List<MessageRecu> lstMessagesRecu =  messageRecu.getMessageRecuByMessage(Long.valueOf(idMsg));
		LOGGER.info("Taille liste messages recus :" + lstMessagesRecu.size());
		for (MessageRecu monMsg : lstMessagesRecu) {
			LOGGER.info("Utilisateur :" + monMsg.getUtilisateur());
		}
		model.addAttribute("message", message);
		model.addAttribute("lstMessagesRecu", lstMessagesRecu);
		return "Visualiser_Message";
	}
}
