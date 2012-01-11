/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unijorge.baseconhecimento.persistence.dao;

import java.util.List;

import javax.persistence.Query;

import br.unijorge.baseconhecimento.model.entity.Topico;

/**
 * 
 * @author reginaldo.neto
 */
public class TopicoDao extends genericDao<Topico> {
	@SuppressWarnings("unchecked")
	public List<Topico> findbyFilter(Topico topico) {

		StringBuilder str = new StringBuilder();

		str.append("from Topico t where ");

		if (topico.getDescricao() != null && !topico.getDescricao().equals("")) {
			str.append(" lower(t.descricao) like :descricao ");
		}

		if ((topico.getDescricao() != null && !topico.getDescricao().equals(""))&& (topico.getAssunto().getId() != null && topico.getAssunto().getId() != 0)) {
			str.append(" and ");
		}

		if (topico.getAssunto().getId() != null
				&& topico.getAssunto().getId() != 0) {
			str.append(" t.assunto.id =:idassunto ");
		}

		Query query = this.getEntityManager().createQuery(str.toString());

		if (topico.getDescricao() != null && !topico.getDescricao().equals("")) {
			query.setParameter("descricao", "%"
					+ topico.getDescricao().toLowerCase() + "%");
		}

		if (topico.getAssunto().getId() != null
				&& topico.getAssunto().getId() != 0) {
			query.setParameter("idassunto", topico.getAssunto().getId());
		}
		return query.getResultList();
	}
}
