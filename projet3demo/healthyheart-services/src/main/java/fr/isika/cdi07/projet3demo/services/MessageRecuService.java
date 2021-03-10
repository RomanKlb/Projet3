package fr.isika.cdi07.projet3demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.cdi07.projet3demo.dao.MessageRecuRepository;
import fr.isika.cdi07.projet3demo.model.MessageRecu;
import fr.isika.cdi07.projet3demo.model.Utilisateur;

@Service
public class MessageRecuService {
	
	@Autowired
	private MessageRecuRepository messageRecuRepo;
	
	public List<MessageRecu> afficherMessagesRecu() {
		return messageRecuRepo.findAll();
	}
	
	public Long compterMessages() {
		return messageRecuRepo.count();
	}
	
	public MessageRecu ajout(MessageRecu msgrecu) {
		return messageRecuRepo.save(msgrecu);
	}

	public MessageRecu getMessageById(Long msgId) {
		return messageRecuRepo.getOne(msgId);
	}

	public List<MessageRecu> getMessageRecuByMessage(Long idMsg) {
		List<MessageRecu> LstMessages = messageRecuRepo.findAll();
		List<MessageRecu> LstMessagesSel = new ArrayList<MessageRecu>();
		LstMessages.stream().filter(p-> p.getMessageInterne().getIdMessage() == idMsg).
				forEach(p -> LstMessagesSel.add(p));
		return LstMessagesSel;
	}

	public List<MessageRecu> afficherUserMessages(Utilisateur utilisateur) {
		List<MessageRecu> LstMessages = messageRecuRepo.findAll();
		List<MessageRecu> LstMessagesSel = new ArrayList<MessageRecu>();
		LstMessages.stream().filter(p-> p.getUtilisateur() == utilisateur).
				forEach(p -> LstMessagesSel.add(p));
		return LstMessagesSel;
	}
}
