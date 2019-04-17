package br.com.tidicas.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.tidicas.dados.Categoria;
import br.com.tidicas.dao.CategoriaDao;

@Stateless
@Local(CategoriaBusiness.class)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CategoriaBusinessImpl implements CategoriaBusiness, Serializable {

  private static final long serialVersionUID = -1965283677653488122L;
  private static final Logger LOGGER = Logger.getLogger(CategoriaBusinessImpl.class.getName());

  @Inject
  private CategoriaDao categoriaDao;

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public void adiciona(Categoria categoria) {

    try {

      /*
       * if (blog.getId() == null || blog.getConteudo() == null) { throw new NullPointerException(
       * "Nenhum parametro de entrada pode ser nulo"); }
       */
      categoria.setId(null);
      categoriaDao.adiciona(categoria);

    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage());
    }

  }

  @Override
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
  public List<Categoria> listaTodos() {
    List<Categoria> result = new ArrayList<Categoria>();

    try {

      result = categoriaDao.lista();

    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage());
    }

    return result;
  }

  @Override
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
  public Categoria busca(Long id) {
    Categoria result = null;

    try {

      if (id == null) {
        throw new NullPointerException("Nenhum parametro de entrada pode ser nulo");
      }

      result = categoriaDao.busca(id);

    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage());
    }
    return result;
  }

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public Categoria atualiza(Categoria categoria) {
    Categoria result = null;

    try {

      if (categoria.getId() == null) {
        throw new NullPointerException("A propriedade Id nao pode ser nula");
      }

      result = categoriaDao.busca(categoria.getId());
      result.setDescricao(categoria.getDescricao());

      result = categoriaDao.atualiza(categoria);

    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage());
    }

    return result;
  }

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public void deleta(Long id) {
    try {

      if (id == null) {
        throw new NullPointerException("O parametro id nao pode ser nulo");
      }

      categoriaDao.deleta(id, Categoria.class);

    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage());
    }

  }

}