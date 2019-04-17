package br.com.tidicas.dao;

import javax.enterprise.context.Dependent;

import br.com.tidicas.dados.Blog;

/**
 * 
 * @author Evaldo Junior
 * 
 * 
 */
@Dependent
public class BlogDao extends GenericDao<Blog> {

	public BlogDao() {
		super(Blog.class);
	}

}