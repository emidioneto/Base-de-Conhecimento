/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unijorge.baseconhecimento.persistence.dao;

import java.util.List;

import javax.persistence.Query;

import br.unijorge.baseconhecimento.controller.business.impl.AssuntoBO;
import br.unijorge.baseconhecimento.excessao.BusinessExceptions;
import br.unijorge.baseconhecimento.model.entity.Assunto;
import br.unijorge.baseconhecimento.model.entity.Questionario;

/**
 *
 * @author reginaldo.neto
 */
public class QuestionarioDao extends genericDao<Questionario> {
	AssuntoBO assuntoBO = new AssuntoBO();
	@Override
	public void insert(Questionario obj) throws BusinessExceptions{
		try {
			this.getEntityManager().getTransaction().begin();
			Assunto a = assuntoBO.obter(obj.getAssunto().getId());
			obj.setAssunto(a);
			this.getEntityManager().persist(obj);
			this.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			this.getEntityManager().getTransaction().rollback();
			System.out.println(e.getMessage());
		}
	}
	@SuppressWarnings("unchecked")
	public List<Questionario> findbyFilter(Questionario questionario) {

		StringBuilder str = new StringBuilder();
		boolean and = false;
		str.append("from Questionario q ");

		if (questionario.getDescricao() != null && !questionario.getDescricao().equals("")) {
			str.append((and?" and ":" where ") + " lower(q.descricao) like :descricao ");
			and = true;
		}

		if (questionario.getAssunto() != null && questionario.getAssunto().getId() != 0) {
			str.append((and?" and ":" where ") + " q.assunto =:assunto ");
			and = true;
		}

		Query query = this.getEntityManager().createQuery(str.toString());

		if (questionario.getDescricao() != null && !questionario.getDescricao().equals("")) {
			query.setParameter("descricao", "%" + questionario.getDescricao().toLowerCase() + "%");
		}

		if (questionario.getAssunto() != null && questionario.getAssunto().getId() != 0) {
			query.setParameter("assunto", questionario.getAssunto());
		}
		return query.getResultList();
	}
}
