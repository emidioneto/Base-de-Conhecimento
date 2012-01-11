/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unijorge.baseconhecimento.persistence.dao;

import java.util.List;

import javax.persistence.Query;

import br.unijorge.baseconhecimento.excessao.BusinessExceptions;
import br.unijorge.baseconhecimento.model.entity.Assunto;
import br.unijorge.baseconhecimento.model.entity.Topico;

/**
 *
 * @author reginaldo.neto
 */
public class AssuntoDao extends genericDao<Assunto> {
	private TopicoDao topicoDao;
	private Topico topico;
	public AssuntoDao() {
		super();
		this.topico = new Topico();
		this.topicoDao = new TopicoDao();
	}

	@SuppressWarnings("unchecked")
	public List<Assunto> findbyFilter(Assunto assunto) throws BusinessExceptions{
		
		StringBuilder str = new StringBuilder();
		
		str.append("from Assunto a where ");
		
		if(assunto.getDescricao()!=null && !assunto.getDescricao().equals("")){
			str.append(" lower(a.descricao) like :descricao ");
		}
		
		
		
		if((assunto.getDescricao()!=null && !assunto.getDescricao().equals("") && (assunto.getDisciplina().getId() != null && assunto.getDisciplina().getId() != 0))){
			str.append(" and ");
		}
		
		
		if(assunto.getDisciplina().getId() != null && assunto.getDisciplina().getId() != 0){
			str.append(" a.disciplina.id =:iddisciplina ");
		}	
		
		Query query = this.getEntityManager().createQuery(str.toString());
		
		if(assunto.getDescricao()!=null && !assunto.getDescricao().equals("")){
			query.setParameter("descricao", "%"+assunto.getDescricao().toLowerCase()+"%");
		}
		
		if(assunto.getDisciplina().getId() != null && assunto.getDisciplina().getId() != 0){
			query.setParameter("iddisciplina", assunto.getDisciplina().getId());
		}
		return query.getResultList();
	}
	
	@Override
	public void delete(Assunto obj) throws BusinessExceptions {
		this.topico.setAssunto(obj);
		boolean temFilhos = this.topicoDao.findbyFilter(this.topico).size() != 0 ? true : false;
		//TODO verificar se existe algum questionário
		if(!temFilhos){
			super.delete(obj);
		}else{
			throw new BusinessExceptions("Existe tópicos relacionados com este assunto.");
		}
	}
	

	public void merge(Assunto obj) throws BusinessExceptions{
		try {
			this.getEntityManager().getTransaction().begin();
			Assunto assunto = new Assunto();
			assunto = this.findById(obj.getId()); 
			
			assunto.setDescricao(obj.getDescricao());
			assunto.setDisciplina(obj.getDisciplina());
			assunto.setQuestionarios(obj.getQuestionarios());
			assunto.setTopicos(obj.getTopicos());
			
			this.getEntityManager().merge(assunto);
			this.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			this.getEntityManager().getTransaction().rollback();
		}
	}
}
