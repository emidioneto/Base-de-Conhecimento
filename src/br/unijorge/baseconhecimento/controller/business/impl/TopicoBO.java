package br.unijorge.baseconhecimento.controller.business.impl;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;

import br.unijorge.baseconhecimento.excessao.BusinessExceptions;
import br.unijorge.baseconhecimento.model.entity.Topico;
import br.unijorge.baseconhecimento.persistence.dao.TopicoDao;
import br.unijorge.baseconhecimento.util.FacesUtil;

public class TopicoBO{

	TopicoDao topicoDao = new TopicoDao();	
	
	public boolean save(Topico topico) {
		try {
			topicoDao.insert(topico);
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_INFO,
					"Novo tópico", "Tópico Cadastrado!");
			return true;
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN, "Erro!",
					e.getMessage());
			return false;
		}
	}
	
	public boolean edit(Topico topico) {
		try {
			topicoDao.insert(topico);
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_INFO,
					"Editar tópico", "Tópico editado!");
			return true;
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN, "Erro!",
					e.getMessage());
			return false;
		}
	}

	
	public boolean excluir(Topico topico) {
		try {
			topicoDao.delete(topico);
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_INFO,
					"Excluir tópico", "Tópico Excluído!");
			return true;
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN, "Erro!",
					e.getMessage());
			return false;
		}		
	}

	
	public Topico obter(Long id) {
		try {
			return topicoDao.findById(id);
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN, "Erro!",
					e.getMessage());
			return null;
		}
	}

	
	public ArrayList<Topico> listar() {
		try {
			return (ArrayList<Topico>) topicoDao.findAll();
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN, "Erro!",
					e.getMessage());
			return null;
		}
	}

	public ArrayList<Topico> listarPorFiltro(Topico topico) {
		return (ArrayList<Topico>) topicoDao.findbyFilter(topico);
	}
}
