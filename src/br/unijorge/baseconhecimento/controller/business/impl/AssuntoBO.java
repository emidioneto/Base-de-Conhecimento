package br.unijorge.baseconhecimento.controller.business.impl;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;

import br.unijorge.baseconhecimento.excessao.BusinessExceptions;
import br.unijorge.baseconhecimento.model.entity.Assunto;
import br.unijorge.baseconhecimento.persistence.dao.AssuntoDao;
import br.unijorge.baseconhecimento.util.FacesUtil;

public class AssuntoBO {
	AssuntoDao assuntoDao = new AssuntoDao();

	public boolean save(Assunto assunto) {
		try {
			assuntoDao.insert(assunto);
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_INFO,
					"Novo assunto", "Assunto Cadastrado!");
			return true;
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN, "Erro!",
					e.getMessage());
			return false;
		}
	}
	
	public boolean edit(Assunto assunto) {
		try {
			assuntoDao.insert(assunto);
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_INFO,
					"Editar assunto", "Assunto editado!");
			return true;
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN, "Erro!",
					e.getMessage());
			return false;
		}
	}

	public boolean excluir(Assunto assunto) {
		try {
			assuntoDao.delete(assunto);
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_INFO,
					"Novo assunto", "Assunto Excluído!");
			return true;
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN, "Erro!",
					e.getMessage());
			return false;
		}
	}

	public Assunto obter(Long id) {
		try {
			return assuntoDao.findById(id);
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN, "Erro!",
					e.getMessage());
			return null;
		}
	}

	public ArrayList<Assunto> listar() {
		try {
			return (ArrayList<Assunto>) assuntoDao.findAll();
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN, "Erro!",
					e.getMessage());
			return null;
		}
	}

	public ArrayList<Assunto> listarPorFiltro(Assunto assunto) {
		try {
			return (ArrayList<Assunto>) assuntoDao.findbyFilter(assunto);
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN, "Erro!",
					e.getMessage());
			return null;
		}
	}
}
