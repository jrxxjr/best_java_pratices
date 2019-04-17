package br.com.tidicas.business;

import java.util.List;

import javax.ejb.Local;

import br.com.tidicas.dados.Blog;

@Local
public interface BlogBusiness {

  public List<Blog> listaTodos();

  public void adiciona(Blog city);

  public Blog busca(Long id);

  public Blog atualiza(Blog blog);

  public void deleta(Long id);
  
}