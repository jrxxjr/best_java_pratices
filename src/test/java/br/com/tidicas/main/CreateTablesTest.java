package br.com.tidicas.main;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.junit.Test;

import br.com.tidicas.dados.Blog;
import br.com.tidicas.dados.Categoria;
import br.com.tidicas.dao.JpaUtil;

/**
 * Classe para geração das tabelas
 * 
 * @author Evaldo Junior
 *
 */
public class CreateTablesTest {
  private static final Logger LOGGER = Logger.getLogger(CreateTablesTest.class.getName());

  @Test
  public void testCrud() {

    EntityManager em = new JpaUtil().getEntityManager();

    if (em != null) {
      fillData(em);
    }

  }

  private void fillData(EntityManager em) {

    try {

      // 1 Entidade Categoria
      Categoria c = new Categoria();
      c.setDescricao("categoria new");

      em.getTransaction().begin();

      em.persist(c);
      em.getTransaction().commit();

      c.setId((Long) em.createQuery("select max(c.id) from Categoria c").getSingleResult());
      c = em.find(Categoria.class, c.getId());
      LOGGER.info("retorno:" + c.getId());

      em.getTransaction().begin();
      c.setDescricao("categoria update");
      c = em.merge(c);
      em.getTransaction().commit();

      // 2 Entidade Blog
      Blog b = new Blog();
      b.setCategorias(new HashSet<Categoria>());
      Set<Categoria> categorias = new HashSet<Categoria>();
      categorias.add(c);

      for (int i = 0; i < 4; i++) {
        Categoria categoria = new Categoria();
        categoria.setDescricao("categoria new" + i);

        em.getTransaction().begin();
        em.persist(categoria);
        em.getTransaction().commit();

        categoria
            .setId((Long) em.createQuery("select max(c.id) from Categoria c").getSingleResult());
        categoria = em.find(Categoria.class, categoria.getId());
        LOGGER.info("retorno:" + categoria.getId());

        categorias.add(categoria);
      }

      b.setCategorias(categorias);
      b.setConteudo("conteúdo teste");
      b.setContador(0);
      b.setDtevento(new Date());
      b.setPublicar(1);
      b.setTitulo("título");

      em.getTransaction().begin();
      em.persist(b);
      em.getTransaction().commit();

      b.setId((Long) em.createQuery("select max(b.id) from Blog b").getSingleResult());

      b = em.find(Blog.class, b.getId());
      LOGGER.info("retorno:" + b.getTitulo());

      b.setConteudo("conteúdo teste update");
      b.setDtevento(new Date());
      b.setPublicar(1);
      b.setTitulo("título update");
      em.getTransaction().begin();
      b = em.merge(b);
      em.getTransaction().commit();

      b = em.find(Blog.class, b.getId());
      LOGGER.info("retorno:" + b.getTitulo());

      for (Categoria cat : b.getCategorias()) {
        LOGGER.info("categoria: " + cat.getId() + "-" + cat.getDescricao());
      }

      em.getTransaction().begin();
      em.remove(b);
      LOGGER.info("delete blog:" + b.getTitulo());

      for (Categoria categoria : categorias) {
        em.remove(categoria);
        LOGGER.info("delete categoria:" + c.getDescricao());
      }

      em.getTransaction().commit();

    } catch (Exception ex) {
      LOGGER.severe(ex.getMessage());
      em.getTransaction().rollback();
    } finally {
      em.close();
    }

  }

}