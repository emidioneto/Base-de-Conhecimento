package br.unijorge.baseconhecimento.view.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.unijorge.baseconhecimento.controller.business.impl.DisciplinaBO;
import br.unijorge.baseconhecimento.model.entity.Disciplina;
import br.unijorge.baseconhecimento.util.FacesUtil;

@SessionScoped
@ManagedBean(name = "disciplinaBean")
public class DisciplinaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4986316754256027122L;

	private List<Disciplina> disciplinas;
	private DisciplinaBO disciplinaBO;
	private Disciplina disciplina;

	private List<SelectItem> listaDisciplinas;

	public DisciplinaBean() {
		this.disciplinaBO = new DisciplinaBO();
		this.disciplina = new Disciplina();
	}

	/**
	 * @return the disciplinas
	 */
	public List<Disciplina> getDisciplinas() {
		disciplinas = disciplinaBO.listar();
		return disciplinas;
	}

	/**
	 * @param disciplinas
	 *            the disciplinas to set
	 */
	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public String novaDisciplina() {
		return "/restrito/newDisciplina.xhtml";
	}

	/**
	 * @return the disciplina
	 */
	public Disciplina getDisciplina() {
		return disciplina;
	}

	/**
	 * @param disciplina
	 *            the disciplina to set
	 */
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public String salvarDisciplina() {
		if(this.disciplina.getNome().equals("") || (this.disciplina.getNome()==null)){
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN, "Salvar disciplina.", "Nome é um campo requerido");
			return "/restrito/newDisciplina.xhtml" ;
		}else{
			return this.disciplinaBO.save(this.disciplina) == true ? "/restrito/listDisciplinas.xhtml" : "/restrito/newDisciplina.xhtml" ;
		}
			
		//this.limpaCampos();
	}

	public String editarDisciplina() {
		if(this.disciplina.getNome().equals("") || (this.disciplina.getNome()==null)){
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN, "Salvar disciplina.", "Nome é um campo requerido");
			return "/restrito/newDisciplina.xhtml" ;
		}else{
				return this.disciplinaBO.edit(this.disciplina) == true ? "/restrito/listDisciplinas.xhtml" : "/restrito/listDisciplinas.xhtml" ;
		}
			
		//this.limpaCampos();
	}
	
	public String editar(Disciplina disciplina) {
		this.disciplina = disciplina;
		return "/restrito/editDisciplina.xhtml";
	}

	public String excluir(Disciplina disciplina) {
		this.disciplinaBO.excluir(disciplina);
		return "/restrito/listDisciplina.xhtml";
	}

	public List<SelectItem> getListaDisciplinas() {
		List<Disciplina> listDisciplinas;
		this.listaDisciplinas = new ArrayList<SelectItem>();
		listDisciplinas = disciplinaBO.listar();
		for (Disciplina disciplina : listDisciplinas) {
			Disciplina d = disciplina;
			this.listaDisciplinas.add(new SelectItem(d.getId(), d.getNome()));
		}
		return listaDisciplinas;
	}

	public void setListaDisciplinas(List<SelectItem> listaDisciplinas) {
		this.listaDisciplinas = listaDisciplinas;
	}

	public void limpaCampos() {
		this.disciplina = new Disciplina();
	}
}
