package fr.isika.cdi07.projet3demo.builder;

import fr.isika.cdi07.projet3demo.model.Don;

public interface IDonModelBuilder<T extends Don> {
	
	T build(T don);
}
