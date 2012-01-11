/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unijorge.baseconhecimento.persistence.dao;

import java.util.List;

import javax.persistence.Query;

import br.unijorge.baseconhecimento.model.entity.Questao;

/**
 *
 * @author reginaldo.neto
 */
public class QuestaoDao extends genericDao<Questao> {
	@SuppressWarnings("unchecked")
	public List<Questao> findbyFilter(Questao questao) {

		StringBuilder str = new StringBuilder();

		str.append("from Questao q ");
		boolean and = false;
		if (questao.getEnunciado() != null && !questao.getEnunciado().equals("")) {
			str.append((and?" and ":" where ") + " lower(q.enunciado) like :enunciado ");
			and = true;
		}
		if (questao.getQuestionario().getId() != null && questao.getQuestionario().getId() != 0) {
			str.append((and?" and ":" where ") + " q.questionario =:questionario ");
			and = true;
		}

		Query query = this.getEntityManager().createQuery(str.toString());

		if (questao.getEnunciado() != null && !questao.getEnunciado().equals("")) {
			query.setParameter("enunciado", "%"	+ questao.getEnunciado().toLowerCase() + "%");
		}

		if (questao.getQuestionario().getId() != null && questao.getQuestionario().getId() != 0) {
			query.setParameter("questionario", questao.getQuestionario());
		}
		return query.getResultList();
	}
}
