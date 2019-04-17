package br.com.tidicas.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.tidicas.dados.Blog;
import br.com.tidicas.dados.Categoria;
import br.com.tidicas.dao.BlogDao;
import br.com.tidicas.dao.CategoriaDao;

@Stateless
@Local(BlogBusiness.class)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class BlogBusinessImpl implements BlogBusiness, Serializable {

  private static final long serialVersionUID = -1965283677653488122L;
  private static final Logger LOGGER = Logger.getLogger(BlogBusinessImpl.class.getName());

  @Inject
  private BlogDao blogDao;

  @Inject
  private CategoriaDao categoriaDao;

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public void adiciona(Blog blog) {

    try {

      /*
       * if (blog.getId() == null || blog.getConteudo() == null) { throw new NullPointerException(
       * "Nenhum parametro de entrada pode ser nulo"); }
       */
      List<Categoria> list = blog.getCategorias().stream().collect(Collectors.toList());

      for (int i = 0; i < list.size(); i++) {
        Categoria categoria = list.get(i);
        categoria = categoriaDao.busca(categoria.getId());
        list.set(i, categoria);
      }

      blog.setCategorias(list.stream().collect(Collectors.toSet()));

      blogDao.adiciona(blog);

    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage());
    }

  }

  @Override
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
  public List<Blog> listaTodos() {
    List<Blog> result = new ArrayList<Blog>();

    try {

      result = blogDao.lista();

    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage());
    }

    return result;
  }

  @Override
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
  public Blog busca(Long id) {
    Blog result = null;

    try {

      if (id == null) {
        throw new NullPointerException("Nenhum parametro de entrada pode ser nulo");
      }

      result = blogDao.busca(id);

    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage());
    }
    return result;
  }

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public Blog atualiza(Blog blog) {
    Blog result = null;

    try {

      if (blog.getId() == null) {
        throw new NullPointerException("A propriedade Id nao pode ser nula");
      }

      List<Categoria> list = blog.getCategorias().stream().collect(Collectors.toList());

      for (int i = 0; i < list.size(); i++) {
        Categoria categoria = list.get(i);
        categoria = categoriaDao.busca(categoria.getId());
        list.set(i, categoria);
      }

      blog.setCategorias(list.stream().collect(Collectors.toSet()));
      result = blogDao.atualiza(blog);

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

      blogDao.deleta(id, Blog.class);

    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage());
    }

  }

}