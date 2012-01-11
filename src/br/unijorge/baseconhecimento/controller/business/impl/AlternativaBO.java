package br.unijorge.baseconhecimento.controller.business.impl;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;

import br.unijorge.baseconhecimento.excessao.BusinessExceptions;
import br.unijorge.baseconhecimento.model.entity.Alternativa;
import br.unijorge.baseconhecimento.model.entity.Questao;
import br.unijorge.baseconhecimento.persistence.dao.AlternativaDao;
import br.unijorge.baseconhecimento.util.FacesUtil;

public class AlternativaBO {
	AlternativaDao alternativaDao = new AlternativaDao();	
	
	public boolean save(Alternativa alternativa) {
		try {
			alternativaDao.insert(alternativa);
			return true;
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Cadastrar alternativa", "Não foi possível cadastrar!");
			return false;
		}
	}
	
	public boolean edit(Alternativa alternativa){
		try {
			alternativaDao.insert(alternativa);
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Nova alternativa", "Alternativa editada com sucesso!");
			return true;
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Editar alternativa", "NÃ£o foi possível editar!");
			return false;
		}
	}

	
	public boolean excluir(Alternativa alternativa) {
		try {
			alternativaDao.delete(alternativa);		
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Excluir alternativa", "Alternativa excluída com sucesso!");
			return true;
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Excluir alternativa", "NÃ£o foi possível excluir!");
			return false;
		}	
	}

	
	public Alternativa obter(Long id) {
		try {
			return alternativaDao.findById(id);
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN, "Erro!",
					e.getMessage());
			return null;
		}
	}

	
	public ArrayList<Alternativa> listar() {
		try {
			return (ArrayList<Alternativa>) alternativaDao.findAll();
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN, "Erro!",
					e.getMessage());
			return null;
		}
	}
	
	public ArrayList<Alternativa> listarPorQuestao(Questao questao) {
		try {
			return (ArrayList<Alternativa>) alternativaDao.findbyQuestao(questao);
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN, "Erro!",
					e.getMessage());
			return null;
		}
	}
}
