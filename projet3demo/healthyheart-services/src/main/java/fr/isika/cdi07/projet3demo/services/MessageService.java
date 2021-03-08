package fr.isika.cdi07.projet3demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.cdi07.projet3demo.dao.MessageInterneRepository;
import fr.isika.cdi07.projet3demo.model.MessageInterne;

@Service
public class MessageService {
	
	@Autowired
	private MessageInterneRepository messageRepo;
	
	public List<MessageInterne> afficherMessages() {
		return messageRepo.findAll();
	}
	
	public Long compterMessages() {
		return messageRepo.count();
	}
	
	public MessageInterne ajout(MessageInterne msg) {
		return messageRepo.save(msg);
	}

	public MessageInterne getMessageById(Long msgId) {
		return messageRepo.getOne(msgId);
	}

}
