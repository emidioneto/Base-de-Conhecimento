/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unijorge.baseconhecimento.persistence.dao;

import java.util.List;

import javax.persistence.Query;

import br.unijorge.baseconhecimento.model.entity.Disciplina;

/**
 *
 * @author reginaldo.neto
 */
public class DisciplinaDao extends genericDao<Disciplina> {
	@SuppressWarnings("unchecked")
	public List<Disciplina> findbyFilter(Disciplina disciplina) {
		
		StringBuilder str = new StringBuilder();
		
		str.append("from Disciplina d where ");
		
		if(disciplina.getNome()!=null || !disciplina.getNome().equals("")){
			str.append(" lower(d.nome) like :nome ");
		}
		Query query = this.getEntityManager().createQuery(str.toString());
		
		query.setParameter("nome", "%"+disciplina.getNome().toLowerCase()+"%");		
		
		return query.getResultList();
	}
}
