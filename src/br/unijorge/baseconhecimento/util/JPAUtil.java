/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unijorge.baseconhecimento.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author reginaldo.neto
 */
public class JPAUtil {
    	private static final String PERSISTENCE_UNIT_NAME = "basedeconhecimento";
	
	private static final EntityManagerFactory entityManagerFactory = createEntityManagerFactory();
	
	private static EntityManagerFactory createEntityManagerFactory() {
		return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}
	
	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
}
