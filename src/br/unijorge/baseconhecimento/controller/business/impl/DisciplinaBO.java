package br.unijorge.baseconhecimento.controller.business.impl;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;

import br.unijorge.baseconhecimento.excessao.BusinessExceptions;
import br.unijorge.baseconhecimento.model.entity.Disciplina;
import br.unijorge.baseconhecimento.persistence.dao.DisciplinaDao;
import br.unijorge.baseconhecimento.util.FacesUtil;

public class DisciplinaBO{
	DisciplinaDao disciplinaDao = new DisciplinaDao();	
	
	public boolean save(Disciplina disciplina) {
		try {
			disciplinaDao.insert(disciplina);
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Cadastrar disciplina", "Disciplina cadastrada com sucesso!");
			return true;
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Cadastrar disciplina", "Não foi possível cadastrar!");
			return false;
		}
	}

	public boolean edit(Disciplina disciplina) {
		try {
			disciplinaDao.insert(disciplina);
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Nova disciplina", "Disciplina editada com sucesso!");
			return true;
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Editar disciplina", "Não foi possível editar!");
			return false;
		}
	}
	
	public boolean excluir(Disciplina disciplina){
		try {
			disciplinaDao.delete(disciplina);
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Excluir disciplina", "Disciplina excluída com sucesso!");
			return true;
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Excluir disciplina", "Não foi possível excluir!");
			return false;
		}		
	}

	
	public Disciplina obter(Long id){
		try {
			return disciplinaDao.findById(id);
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN, "Erro!",
					e.getMessage());
			return null;
		}
	}

	
	public ArrayList<Disciplina> listar(){
		try {
			return (ArrayList<Disciplina>) disciplinaDao.findAll();
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN, "Erro!",
					e.getMessage());
			return null;
		}
	}

}
