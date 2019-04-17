package br.com.tidicas.bean;

import java.util.Collections;
import java.util.Comparator;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import br.com.tidicas.dados.Blog;
import br.com.tidicas.dao.BlogDao;

/**
 * Classe responsável pela iteração com a camada visual
 * 
 * @author Evaldo Junior
 *
 */
@ManagedBean(name = "blogBean")
@SessionScoped
public class BlogBean {

  private boolean sortAscending = true;

  @Inject
  private BlogDao daoBlog;

  public String sortByOrderCodigo() {
        
    if (sortAscending) {

      Collections.sort(daoBlog.lista(), new Comparator<Blog>() {

        @Override
        public int compare(Blog o1, Blog o2) {
          return o1.getId().compareTo(o2.getId());

        }

      });
      sortAscending = false;

    } else {

      Collections.sort(daoBlog.lista(), new Comparator<Blog>() {

        @Override
        public int compare(Blog o1, Blog o2) {
          return o2.getId().compareTo(o1.getId());
        }

      });
      sortAscending = true;
    }

    return null;
  }

}