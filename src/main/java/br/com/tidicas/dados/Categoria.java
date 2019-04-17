package br.com.tidicas.dados;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Classe para implementar a tabela categoria no banco de dados
 * 
 * @author Evaldo Junior
 */
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {
  
  private static final long serialVersionUID = 1521112277660390970L;
  
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  private String descricao;  

  @ManyToOne(cascade=CascadeType.ALL)
  @JoinColumn(name = "blog_id")
  private Blog blog;  

  public Categoria() {
    super();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescricao() {
    return this.descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Blog getBlog() {
    return blog;
  }

  public void setBlog(Blog blog) {
    this.blog = blog;
  }

}