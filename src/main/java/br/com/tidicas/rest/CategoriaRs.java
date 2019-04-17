package br.com.tidicas.rest;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.tidicas.business.CategoriaBusiness;
import br.com.tidicas.dados.Categoria;
import br.com.tidicas.util.NumberParam;

@Stateless
@Path("/categoria")
public class CategoriaRs {

  /* Toda regra de negocio vai no ejb, nao pode ter regra de negocio no webservice, nesta classe so
   * tem as chamadas para os EJB's 
   */

  @Inject
  private CategoriaBusiness categoriaBusiness;
  private static final Logger LOGGER = Logger.getLogger(CategoriaRs.class.getName());

  /*
   * Propositalmente, o nome do metodo esta errado, o nome correto do metodo e buscaTodas, nao
   * precisa de Categorias no nome
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Categoria> buscaTodasCategorias() {
    List<Categoria> result = null;

    try {

      result = categoriaBusiness.listaTodos();

    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage());
    }

    return result;

  }

  /*
   * Propositalmente, o nome do metodo esta errado, o nome correto do metodo e busca, nao precisa de
   * Categoria no nome
   */
  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response buscaCategoria(@PathParam("id") String id) {
    Categoria result = null;

    try {

      result = categoriaBusiness
          .busca(new NumberParam(Integer.parseInt(id)).getNumber().longValue());

    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage());
    }

    return Response.status(200).entity(result).build();

  }

  @POST
  @Path("")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response adiciona(Categoria categoria) {

    try {

      categoriaBusiness.adiciona(categoria);

    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage());
    }

    return Response.status(200).entity(categoria).build();
  }

  @PUT
  @Path("")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response atualiza(Categoria categoria) {

    try {

      categoriaBusiness.atualiza(categoria);

    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage());
    }

    return Response.status(200).entity(categoria).build();
  }

  @DELETE
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response deleta(@PathParam("id") String id) {
    try {

      categoriaBusiness.deleta(new NumberParam(Integer.parseInt(id)).getNumber().longValue());

    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage());
    }

    return Response.status(200).build();
  }

}