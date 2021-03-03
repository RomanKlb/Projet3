package fr.isika.cdi07.projet3demo.dao;

import org.springframework.data.repository.CrudRepository;

import fr.isika.cdi07.projet3demo.model.Facture;

public interface FactureRepository extends CrudRepository<Facture, Long>{

}
