/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unijorge.baseconhecimento.persistence.dao;

import java.util.List;

import javax.persistence.Query;

import br.unijorge.baseconhecimento.excessao.BusinessExceptions;
import br.unijorge.baseconhecimento.model.entity.Alternativa;
import br.unijorge.baseconhecimento.model.entity.Questao;

/**
 *
 * @author reginaldo.neto
 */
public class AlternativaDao extends genericDao<Alternativa> {
	@SuppressWarnings("unchecked")
	public List<Alternativa> findbyQuestao(Questao questao) throws BusinessExceptions{

		StringBuilder str = new StringBuilder();

		str.append("from Alternativa q ");
		boolean and = false;
		if (questao != null && questao.getId() != null && questao.getId() != 0) {
			str.append((and?" and ":" where ") + " q.questao =:questao ");
			and = true;
		}
		Query query = this.getEntityManager().createQuery(str.toString());
		if (questao != null && questao.getId() != null && questao.getId() != 0) {
			query.setParameter("questao", questao);
		}
		return query.getResultList();
	}    
}
