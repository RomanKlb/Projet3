package fr.isika.cdi07.projet3demo.controller;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

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
public class MessagerieController {

	private static final Logger LOGGER = Logger.getLogger(MessagerieController.class.getSimpleName());

	@Autowired
	private MessageService messageService;

	@Autowired
	private MessageRecuService messageRecuService;

	@Autowired
	private UtilisateurService utilisateurService;


	@GetMapping("/afficherListeUserMessages")
	public String afficherListeUserMessage(Model model, HttpSession session) {
		String userEmail = (String) session.getAttribute("emailUtilisateurConnecte");
		if (userEmail == null) {
			return "redirect:/showConnexionForm";
		}

		Utilisateur tstUser = utilisateurService.chercherUtilisateurParEmail(userEmail);
		if (tstUser == null) {
			return "ErrorSite";
		}

		List<MessageRecu> userMessages = messageRecuService.afficherUserMessages(tstUser);

		model.addAttribute("count", userMessages.size());
		model.addAttribute("messagesList", userMessages);
		return "listeUserMessages";
	}

	@GetMapping("/afficherListeMessages")
	public String afficherListeMessage(Model model) {
		model.addAttribute("count", messageService.compterMessages());
		model.addAttribute("messagesList", messageService.afficherMessages());
		return "listeMessages";
	}

	@GetMapping("/ajouterUserMessage")
	public String ajouterNouveauUserMessage(Model model, HttpSession session) {
		String userEmail = (String) session.getAttribute("emailUtilisateurConnecte");
		if (userEmail == null) {
			return "redirect:/showConnexionForm";
		}

		Utilisateur tstUser = utilisateurService.chercherUtilisateurParEmail(userEmail);
		if (tstUser == null) {
			return "ErrorSite";
		}

		MessageInterne messageTst = new MessageInterne();
		messageTst.setEmetteur(tstUser);
		MessagerieForm nouveauMessage = new MessagerieForm();
		nouveauMessage.setMessage(messageTst);
		model.addAttribute("messagerieForm", nouveauMessage);
		return "nouveau_user_message";
	}

	@GetMapping("/ajouterMessage")
	public String ajouterNouveauMessage(Model model) {
		MessagerieForm nouveauMessage = new MessagerieForm();
		model.addAttribute("messagerieForm", nouveauMessage);
		return "nouveau_message";
	}

	// @RequestMapping(value = "/sauvegarderMessage", method = RequestMethod.POST)
	@PostMapping(value = "/sauvegarderMessage")
	public String sauvegarderMessage(@ModelAttribute("messagerieForm") MessagerieForm messagerieForm, Model model) {
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

		for (Utilisateur userDest : lstUserDest) {
			MessageRecu monDest = new MessageRecu();
			monDest.setDateHeure(dateMsg);
			monDest.setMessageInterne(messagerieForm.getMessage());
			monDest.setisRead(false);
			monDest.setUtilisateur(userDest);

			messageRecuService.ajout(monDest);
		}

		return "redirect:/afficherListeMessages";
	}

	@PostMapping(value = "/sauvegarderUserMessage")
	public String sauvegarderUserMessage(@ModelAttribute("messagerieForm") MessagerieForm messagerieForm, Model model) {

		// traitement des déstinataires

		System.out.println("infos listDestinataire :" + messagerieForm.getListeDest());
		String[] tabDest = messagerieForm.getListeDest().replace(',', ';').trim().split(";");

		if (tabDest.length == 0) {
			String errMsg = "Il n'y aucun destinataire saisi";
			LOGGER.info(errMsg);
			model.addAttribute("msgerr", errMsg);
			return "nouveau_user_message";
		}

		HashSet<Utilisateur> lstUserDest = new HashSet<Utilisateur>();

		for (String monDest : tabDest) {
			String email = monDest.trim();
			Utilisateur tstDest = utilisateurService.chercherUtilisateurParEmail(email);
			if (tstDest == null) {
				String errMsg = "Le destinataire avec l'email " + email + " est inconnu dans notre référentiel";
				LOGGER.info(errMsg);
				model.addAttribute("msgerr", errMsg);
				return "nouveau_user_message";
			}
			if (!lstUserDest.add(tstDest)) {
				String errMsg = "Le destinataire avec l'email " + email + " est en doubel dans les destinataires";
				LOGGER.info(errMsg);
				model.addAttribute("msgerr", errMsg);
				return "nouveau_user_message";
			}
		}

		// insert message
		Date dateMsg = Date.from(Instant.now());
		messagerieForm.getMessage().setDate(dateMsg);
		messageService.ajout(messagerieForm.getMessage());

		for (Utilisateur userDest : lstUserDest) {
			MessageRecu monDest = new MessageRecu();
			monDest.setDateHeure(dateMsg);
			monDest.setMessageInterne(messagerieForm.getMessage());
			monDest.setisRead(false);
			monDest.setUtilisateur(userDest);

			messageRecuService.ajout(monDest);
		}

		return "redirect:/afficherListeUserMessages";
	}

	@GetMapping("/VisualierMessage/{idMsg}")
	public String VisualierMessage(@PathVariable String idMsg, Model model) {
		// LOGGER.info("id Msg dans VisualierMessage :" + idMsg);
		MessageInterne message = messageService.getMessageById(Long.valueOf(idMsg));
		List<MessageRecu> lstMessagesRecu = messageRecuService.getMessageRecuByMessage(Long.valueOf(idMsg));
		LOGGER.info("Taille liste messages recus :" + lstMessagesRecu.size());
		for (MessageRecu monMsg : lstMessagesRecu) {
			LOGGER.info("Utilisateur :" + monMsg.getUtilisateur());
		}
		model.addAttribute("message", message);
		model.addAttribute("lstMessagesRecu", lstMessagesRecu);
		return "Visualiser_Message";
	}

	@GetMapping("/VisualierUserMessage/{idMsg}")
	public String VisualierUserMessage(@PathVariable Long idMsg, Model model) {
		// LOGGER.info("Id MsgRecu : " + idMsg);
		MessageRecu messageRecu = messageRecuService.getMessageById(idMsg);
		MessageInterne message = messageRecu.getMessageInterne();
		model.addAttribute("message", message);
		String infoLu = "Non lu";
		if (messageRecu.isIsRead()) {
			SimpleDateFormat dateForm = new SimpleDateFormat("EEEEE dd/MM/yyyy à HH:mm:ss");
			infoLu = "Lu le " + dateForm.format(messageRecu.getDateHeure());
		}
		model.addAttribute("infoLu", infoLu);
		
		// update info lu
		if (!messageRecu.isIsRead()) {
			messageRecu.setisRead(true);
			messageRecu.setDateHeure(Date.from(Instant.now()));
		}
		messageRecuService.ajout(messageRecu);
		return "Visualiser_User_Message";
	}
}
