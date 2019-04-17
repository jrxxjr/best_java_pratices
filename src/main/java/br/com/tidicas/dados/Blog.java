package br.com.tidicas.dados;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "blog")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id","conteudo","titulo","dtevento","contador","publicar","categoria"})
public class Blog {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @JsonProperty("id")
  private Long id;
  
  @JsonProperty("conteudo")
  private String conteudo;
  
  @JsonProperty("titulo")
  private String titulo;
  
  @Temporal(TemporalType.DATE)
  @JsonProperty("dtevento")
  private Date dtevento;
  
  @JsonProperty("contador")
  private Integer contador;
  
  @JsonProperty("publicar")
  private Integer publicar;
  
  @ManyToOne(cascade=CascadeType.ALL)
  @JoinColumn(name="categoria")
  @JsonProperty("categoria")
  private Categoria categoria;
  
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "categorias_id")
  @JsonProperty("categorias")
  private Set<Categoria> categorias;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getConteudo() {
    return conteudo;
  }

  public void setConteudo(String conteudo) {
    this.conteudo = conteudo;
  }
  
  public String getTitulo() {
    return titulo;
  }
  
  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }
  
  public Date getDtevento() {
    return dtevento;
  }
  
  public void setDtevento(Date dtevento) {
    this.dtevento = dtevento;
  }
  
  public Integer getContador() {
    return contador;
  }
  
  public void setContador(Integer contador) {
    this.contador = contador;
  }
  
  public Integer getPublicar() {
    return publicar;
  }
  
  public void setPublicar(Integer publicar) {
    this.publicar = publicar;
  }
  
  public Set<Categoria> getCategorias() {
    return categorias;
  }

  public void setCategorias(Set<Categoria> categorias) {
    this.categorias = categorias;
  }
 
}