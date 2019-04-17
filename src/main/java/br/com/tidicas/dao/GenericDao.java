package br.com.tidicas.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

@Dependent
public abstract class GenericDao<T> {
  private final static String UNIT_NAME = "soaprest";
  private static final Logger LOGGER = Logger.getLogger(GenericDao.class.getName());
  
  @PersistenceContext(unitName = UNIT_NAME)
  private EntityManager em;

  private Class<T> entityClass;

  public GenericDao(Class<T> entityClass) {
    this.entityClass = entityClass;
  }

  public void adiciona(T entity) {
    em.persist(entity);
  }

  public void deleta(Object id, Class<T> classe) {
    T entityToBeRemoved = em.getReference(classe, id);
    em.remove(entityToBeRemoved);
  }

  public T atualiza(T entity) {
    return em.merge(entity);
  }

  public T busca(Long entityID) {
    return em.find(entityClass, entityID);
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public List<T> lista() {
    CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
    cq.select(cq.from(entityClass));
    return em.createQuery(cq).getResultList();
  }

  @SuppressWarnings("unchecked")
  public T buscaUmResultado(String namedQuery, Map<String, Object> parameters) {
    T result = null;

    try {
      Query query = em.createQuery(namedQuery);

      if (parameters != null && !parameters.isEmpty()) {
        populateQueryParameters(query, parameters);
      }

      result = (T) query.getSingleResult();

    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Erro enquanto executava query: ", e.getCause());
    }

    return result;
  }

  @SuppressWarnings("unchecked")
  public List<T> buscaResultados(String namedQuery, Map<String, Object> parameters) {
    List<T> result = null;

    try {
      Query query = em.createQuery(namedQuery);

      if (parameters != null && !parameters.isEmpty()) {
        populateQueryParameters(query, parameters);
      }

      result = query.getResultList();

    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Erro enquanto executava query: ", e.getCause());
    }

    return result;
  }

  private void populateQueryParameters(Query query, Map<String, Object> parameters) {

    for (Entry<String, Object> entry : parameters.entrySet()) {
      query.setParameter(entry.getKey(), entry.getValue());
    }
  }
}