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

import br.com.tidicas.business.CategoriaBusiness;
import br.com.tidicas.dados.Categoria;
import br.com.tidicas.util.NumberParam;

@WebService(targetNamespace = "http://localhost:8080/bestpratices", name = "CategoriaEndpoint", serviceName = "CategoriaEndPointService")
@SOAPBinding(style = Style.RPC)

/**
 * WebService responsavel pelo retorno de informacoes de Categoria
 * 
 */
public class CategoriaEndPoint {

  /* Toda regra de negocio vai no ejb, nao pode ter regra de negocio no webservice, nesta classe so
   * tem as chamadas para os EJB's
   */

  private static final Logger LOGGER = Logger.getLogger(CategoriaEndPoint.class.getName());

  @Inject
  private CategoriaBusiness categoriaBusiness;

  /*
   * Propositalmente, o nome do metodo esta errado, o nome correto do metodo e busca, nao precisa de
   * Categoria no nome
   */
  @WebMethod(operationName = "buscaCategoria", action = "buscaCategoria")
  public Categoria buscaCategoria(@WebParam(name = "id") Long id) {

    Categoria result = categoriaBusiness.busca(new NumberParam(id).getNumber().longValue());

    return result;
  }

  /*
   * Propositalmente, o nome do metodo esta errado, o nome correto do metodo e buscaTodas, nao
   * precisa de Categorias no nome
   */
  @WebMethod(operationName = "buscaTodasCategorias", action = "buscaTodasCategorias")
  public List<Categoria> buscaTodasCategorias() throws RemoteException {
    List<Categoria> result = null;

    try {

      result = categoriaBusiness.listaTodos();

    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage());
    }

    return result;

  }

  @WebMethod(operationName = "adiciona", action = "adiciona")
  public void adiciona(Categoria categoria) throws RemoteException {

    try {

      categoriaBusiness.adiciona(categoria);

    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage());
    }

  }

  @WebMethod(operationName = "atualiza", action = "atualiza")
  public Categoria atualiza(Categoria categoria) throws RemoteException {
    Categoria result = null;

    try {

      result = categoriaBusiness.atualiza(categoria);

    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage());
    }

    return result;
  }

  @WebMethod(operationName = "deleta", action = "deleta")
  public void deleta(Long id) throws RemoteException {
    try {

      categoriaBusiness.deleta(new NumberParam(id).getNumber().longValue());

    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage());
    }

  }

}