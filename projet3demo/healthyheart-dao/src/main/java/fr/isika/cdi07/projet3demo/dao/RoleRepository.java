package fr.isika.cdi07.projet3demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.isika.cdi07.projet3demo.model.Role;
import fr.isika.cdi07.projet3demo.model.TypeRole;

public interface RoleRepository extends JpaRepository<Role, Long>{

	List<Role> findAllByTypeRole(TypeRole typeRole);

}
