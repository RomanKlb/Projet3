package fr.isika.cdi07.projet3demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import fr.isika.cdi07.projet3demo.model.Role;
import fr.isika.cdi07.projet3demo.model.TypeRole;
import fr.isika.cdi07.projet3demo.model.Utilisateur;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Optional<Role> findByTypeRoleAndUtilisateur(TypeRole typeRole, Utilisateur utilisateur);
}
