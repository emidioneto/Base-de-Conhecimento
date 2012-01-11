package br.unijorge.baseconhecimento.controller.business.impl;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;

import br.unijorge.baseconhecimento.excessao.BusinessExceptions;
import br.unijorge.baseconhecimento.model.entity.Questao;
import br.unijorge.baseconhecimento.persistence.dao.QuestaoDao;
import br.unijorge.baseconhecimento.util.FacesUtil;

public class QuestaoBO {
	QuestaoDao questaoDao = new QuestaoDao();	
	
	public boolean save(Questao questao){
		try {
			questaoDao.insert(questao);
			return true;
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Cadastrar Questão", "Não foi possivel cadastrar!");
			return false;
		}
	}
	
	public boolean edit(Questao questao){
		try {
			questaoDao.insert(questao);
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Nova Questão", "Questão editada com sucesso!");
			return true;
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Editar Questão", "Não foi possivel editar!");
			return false;
		}
	}
	
	public boolean excluir(Questao questao) {
		try {
			questaoDao.delete(questao);		
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Excluir Questão", "Questão excluída com sucesso!");
			return true;
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Excluir Questão", "Não foi possivel excluir!");
			return false;
		}	
	}

	
	public Questao obter(Long id) {
		try {
			return questaoDao.findById(id);
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN, "Erro!",
					e.getMessage());
			return null;
		}
	}

	
	public ArrayList<Questao> listar() {
		try {
			return (ArrayList<Questao>) questaoDao.findAll();
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN, "Erro!",
					e.getMessage());
			return null;
		}
	}
	
	public ArrayList<Questao> listarPorFiltro(Questao questao) {
		return (ArrayList<Questao>) questaoDao.findbyFilter(questao);
	}
	
}
