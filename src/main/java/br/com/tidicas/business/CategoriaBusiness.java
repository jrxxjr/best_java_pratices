package br.com.tidicas.business;

import java.util.List;

import javax.ejb.Local;

import br.com.tidicas.dados.Categoria;

@Local
public interface CategoriaBusiness {

  public List<Categoria> listaTodos();

  public void adiciona(Categoria city);

  public Categoria busca(Long id);

  public Categoria atualiza(Categoria blog);

  public void deleta(Long id);
  
}