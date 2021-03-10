package fr.isika.cdi07.projet3demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import fr.isika.cdi07.projet3demo.dao.RoleRepository;
import fr.isika.cdi07.projet3demo.model.Role;
import fr.isika.cdi07.projet3demo.model.TypeRole;
import fr.isika.cdi07.projet3demo.model.Utilisateur;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepo;


	public Role hasRole(Utilisateur user, TypeRole typeRole) {

		List<Role> roles = roleRepo.findAll();
		Optional<Role> optRole = roles.stream().filter(r -> r.getUtilisateur().equals(user) && r.getTypeRole().equals(typeRole)).findFirst();
		if(optRole.isPresent())
			return optRole.get();
		
		Role newRole = new Role();
		newRole.setTypeRole(typeRole);
		newRole.setUtilisateur(user);
		

		return roleRepo.save(newRole);
	}
	
	public Optional<Role> testIsPorteurProjet(Utilisateur user) {
		List<Role> listeRolePorteurProjet = roleRepo.findAllByTypeRole(TypeRole.PORTEURPROJET);
		return listeRolePorteurProjet.stream()
				.filter(r -> r.getUtilisateur()
				.equals(user)).findFirst();
			
	}

	
	
	
	
	
}
