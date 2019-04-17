package br.com.tidicas.dao;

import javax.enterprise.context.Dependent;

import br.com.tidicas.dados.Categoria;

/**
 * 
 * @author Evaldo Junior
 * 
 * 
 */
@Dependent
public class CategoriaDao extends GenericDao<Categoria> {

	public CategoriaDao() {
		super(Categoria.class);
	}

}