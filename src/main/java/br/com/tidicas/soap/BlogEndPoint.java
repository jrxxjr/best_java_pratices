package br.com.tidicas.soap;

import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import br.com.tidicas.business.BlogBusiness;
import br.com.tidicas.dados.Blog;
import br.com.tidicas.util.NumberParam;

@WebService(targetNamespace = "http://localhost:8080/bestpratices", name = "BlogEndpoint", serviceName = "BlogEndPointService")
@SOAPBinding(style = Style.RPC)

/**
 * WebService responsavel pelo retorno de informacoes de Blog
 * 
 */
public class BlogEndPoint {

  /* Toda regra de negocio vai no ejb, nao pode ter regra de negocio no webservice, nesta classe so
   * tem as chamadas para os EJB's
   */

  private static final Logger LOGGER = Logger.getLogger(BlogEndPoint.class.getName());

  @Inject
  private BlogBusiness blogBusiness;

  /*
   * Propositalmente, o nome do metodo esta errado, o nome correto do metodo e busca, nao precisa de
   * Blog no nome
   */
  @WebMethod(operationName = "buscaBlog", action = "buscaBlog")
  public Blog buscaBlog(@WebParam(name = "id") String id) {

    Blog result = blogBusiness.busca(new NumberParam(Long.parseLong(id)).getNumber().longValue());

    return result;
  }

  /*
   * Propositalmente, o nome do metodo esta errado, o nome correto do metodo e buscaTodos, nao
   * precisa de Blogs no nome
   */
  @WebMethod(operationName = "buscaTodosBlogs", action = "buscaTodosBlogs")
  public List<Blog> buscaTodosBlogs() throws RemoteException {
    List<Blog> result = null;

    try {

      result = blogBusiness.listaTodos();

    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage());
    }

    return result;

  }

  @WebMethod(operationName = "adiciona", action = "adiciona")
  public void adiciona(Blog blog) throws RemoteException {

    try {

      blogBusiness.adiciona(blog);

    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage());
    }

  }

  @WebMethod(operationName = "atualiza", action = "atualiza")
  public Blog atualiza(Blog blog) throws RemoteException {
    Blog result = null;

    try {

      result = blogBusiness.atualiza(blog);

    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage());
    }

    return result;
  }

  @WebMethod(operationName = "deleta", action = "deleta")
  public void deleta(Long id) throws RemoteException {
    try {

      blogBusiness.deleta(new NumberParam(id).getNumber().longValue());

    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage());
    }

  }

}