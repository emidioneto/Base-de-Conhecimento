/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unijorge.baseconhecimento.persistence.dao;

import br.unijorge.baseconhecimento.excessao.BusinessExceptions;
import br.unijorge.baseconhecimento.util.JPAUtil;
import java.lang.reflect.ParameterizedType;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author reginaldo.neto
 */
public class genericDao<T> {
    private EntityManager entityManager;
    private Class<T> persistenceClass;

    @SuppressWarnings("unchecked")
	public genericDao() {
        this.entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();  
        this.persistenceClass = (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];        
    }
    public void insert(T obj) throws BusinessExceptions{
		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(obj);
			this.entityManager.getTransaction().commit();
		} catch (Exception e) {
			this.entityManager.getTransaction().rollback();
			System.out.println(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll() throws BusinessExceptions{
		StringBuilder str = new StringBuilder();
		str.append("from "+persistenceClass.getSimpleName()+" t ");
		Query query = this.entityManager.createQuery(str.toString());
		this.entityManager.clear();
		return query.getResultList();
	}
	
	
	public T findById(Long id) throws BusinessExceptions{
		this.entityManager.clear();
		return  (T) this.entityManager.find(persistenceClass, id);
	}
	
	public void delete(T obj) throws BusinessExceptions{
		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.remove(obj);
			this.entityManager.getTransaction().commit();
		} catch (Exception e) {
			this.entityManager.getTransaction().rollback();
		}
	}
	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
