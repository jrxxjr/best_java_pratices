package br.com.tidicas.util;

import java.util.HashSet;

import br.com.tidicas.dados.Blog;
import br.com.tidicas.dados.Categoria;

public class Util {

  public static Blog fillBlog(String id, String conteudo, String titulo, String dtevento,
      String contador, String publicar, String idCategoria) {

    Blog result = new Blog();
    result.setId(new NumberParam(Long.parseLong(id)).getNumber().longValue());
    result.setConteudo(conteudo);
    result.setTitulo(titulo);
    result.setDtevento(new DateParam(dtevento).getDate());
    result.setContador(new NumberParam(Integer.parseInt(contador)).getNumber().intValue());
    result.setPublicar(new NumberParam(Integer.parseInt(publicar)).getNumber().intValue());

    if (idCategoria != null) {
      result.setCategorias(new HashSet<Categoria>());
      Categoria categoria = new Categoria();
      categoria.setId(new NumberParam(Integer.parseInt(idCategoria)).getNumber().longValue());
      result.getCategorias().add(categoria);
    }

    // se for inclusao
    if (result.getId() == 0) {
      result.setId(null);
    }

    return result;
  }

  public static Categoria fillCategoria(String id, String descricao) {

    Categoria result = new Categoria();

    result.setId(new NumberParam(Long.parseLong(id)).getNumber().longValue());
    result.setDescricao(descricao);
    
    // se for inclusao
    if (result.getId() == 0) {
      result.setId(null);
    }

    return result;
  }

}