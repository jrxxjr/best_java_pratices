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

import br.com.tidicas.business.BlogBusiness;
import br.com.tidicas.dados.Blog;
import br.com.tidicas.util.NumberParam;
import br.com.tidicas.util.Util;

@Stateless
@Path("/blog")
public class BlogRs {

  /* Toda regra de negocio vai no ejb, nao pode ter regra de negocio no webservice, 
   * nesta classe so tem as chamadas para os EJB's
   */
  
  @Inject
  private BlogBusiness blogBusiness;
  private static final Logger LOGGER = Logger.getLogger(BlogRs.class.getName());

  /* Propositalmente, o nome do metodo esta errado, o nome correto do metodo e buscaTodos, 
     nao precisa de Blog no nome */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Blog> buscaTodosBlogs() {
    List<Blog> result = null;

    try {
      
      result = blogBusiness.listaTodos();

    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage());
    }

    return result;

  }

  /* Propositalmente, o nome do metodo esta errado, o nome correto do metodo e busca, 
     nao precisa de Blog no nome */
  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response buscaBlog(@PathParam("id") String id) {
    Blog result = null;

    try {

      result = blogBusiness.busca(new NumberParam(Integer.parseInt(id)).getNumber().longValue());

    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage());
    }

    return Response.status(200).entity(result).build();

  }
  
  /* Propositalmente, aqui os parametro estao declarados como Request Body, ou Path Param pela url, mas o padrao em RestFul, 
   * para os metodos POST e PUT, e por Form Url Encoded, ou Form Param.*/
  @POST
  @Path("{conteudo}/{titulo}/{dtevento}/{contador}/{publicar}/{categoria}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response adiciona(@PathParam("conteudo") String conteudo, @PathParam("titulo") String titulo,
      @PathParam("dtevento") String dtevento, @PathParam("contador") String contador,
      @PathParam("publicar") String publicar, @PathParam("categoria") String idCategoria) {

    Blog blog = null;

    try {

      blog = Util.fillBlog("0", conteudo, titulo, dtevento, contador, publicar, idCategoria);

      blogBusiness.adiciona(blog);

    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage());
    }
    
    return Response.status(200).entity(blog).build();
  }

  /* Propositalmente, aqui os parametro estao declarados como Request Body, ou Path Param pela url, mas o padrao em RestFul, 
   * para os metodos POST e PUT, e por Form Url Encoded, ou Form Param.*/
  @PUT
  @Path("{id}/{conteudo}/{titulo}/{dtevento}/{contador}/{publicar}/{categoria}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response atualiza(@PathParam("id") String id, @PathParam("conteudo") String conteudo, @PathParam("titulo") String titulo,
      @PathParam("dtevento") String dtevento, @PathParam("contador") String contador,
      @PathParam("publicar") String publicar, @PathParam("categoria") String idCategoria) {

    Blog blog = null;

    try {

      blog = Util.fillBlog(id, conteudo, titulo, dtevento, contador, publicar, idCategoria);

      blogBusiness.atualiza(blog);

    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage());
    }

    return Response.status(200).entity(blog).build();
  }
  
  @DELETE
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response deleta(@PathParam("id") String id) {
    try {

      blogBusiness.deleta(new NumberParam(Long.parseLong(id)).getNumber().longValue());

    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage());
    }
   
    return Response.status(200).build();
  }

}