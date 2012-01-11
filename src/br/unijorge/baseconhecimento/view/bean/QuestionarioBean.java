package br.unijorge.baseconhecimento.view.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.unijorge.baseconhecimento.controller.business.impl.AssuntoBO;
import br.unijorge.baseconhecimento.controller.business.impl.QuestionarioBO;
import br.unijorge.baseconhecimento.model.entity.Assunto;
import br.unijorge.baseconhecimento.model.entity.Questionario;

@SessionScoped
@ManagedBean(name="questionarioBean")
public class QuestionarioBean  implements Serializable{

	private static final long serialVersionUID = -2444205513813156467L;

	private List<Questionario> questionarios;
	private QuestionarioBO questionarioBO;
	private Questionario questionario;
	private Questionario questionarioBusca;
	private Long id_assunto;
	private List<SelectItem> listaAssuntos;
	
	
	public QuestionarioBean() {
		this.questionarioBO = new QuestionarioBO();
		this.questionario = new Questionario();
		this.questionarioBusca = new Questionario();
	}

	public List<Questionario> getQuestionarios() {
		return this.questionarios;
	}

	public void setQuestionarios(List<Questionario> questionarios) {
		this.questionarios = questionarios;
	}
	
	public String novoQuestionario(){
		this.questionario = new Questionario();
		return "/restrito/newQuestionario.xhtml";
	}

	public Questionario getQuestionario() {
		return questionario;
	}

	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}

	public Questionario getQuestionarioBusca() {
		return questionarioBusca;
	}

	public void setQuestionarioBusca(Questionario questionarioBusca) {
		this.questionarioBusca = questionarioBusca;
	}

	public Long getId_assunto() {
		return id_assunto;
	}

	public void setId_assunto(Long id_assunto) {
		this.id_assunto = id_assunto;
	}

	public List<SelectItem> getListaAssuntos() {
		List<Assunto> listAssuntos;
		this.listaAssuntos = new ArrayList<SelectItem>();
		listAssuntos = new AssuntoBO().listar();
		for (Assunto assunto : listAssuntos) {
			Assunto a = assunto;
			this.listaAssuntos.add(new SelectItem(a.getId(), a.getDescricao()));
		}

		return listaAssuntos;
	}

	public void setListaAssuntos(List<SelectItem> listaAssuntos) {
		this.listaAssuntos = listaAssuntos;
	}

	public String buscar() {
		if (this.id_assunto != null && this.id_assunto != 0){
			Assunto assunto = new AssuntoBO().obter(this.id_assunto);
			this.questionario.setAssunto(assunto);
			this.questionarios = this.questionarioBO.listarPorFiltro(this.questionario);
		} else {
			this.questionarios = this.questionarioBO.listar();
		}
		return "/restrito/listQuestionarios.xhtml";
	}
	
	public void limpaCamposAposBusca() {
		this.questionario = new Questionario();
	}
	
	public String incluirQuestionario() {
		String retorno = this.questionarioBO.save(this.questionario) == true ? "/restrito/listQuestionarios.xhtml" : "/restrito/newQuestionario.xhtml" ;
		this.limpaCamposAposBusca();
		buscar();
		return retorno;			
	}
	
	public String atualizarQuestionario() {
		if(this.id_assunto != this.questionario.getAssunto().getId()){
			this.questionario.setAssunto(new AssuntoBO().obter(id_assunto));
		}		
		String retorno = this.questionarioBO.edit(this.questionario) == true ? "/restrito/listQuestionarios.xhtml" : "/restrito/editQuestionario.xhtml";
		this.limpaCamposAposBusca();
		return retorno;
	}
	
	public String editar(Questionario questionario) {
		this.questionario = questionario;
		this.id_assunto = this.questionario.getAssunto().getId();
		return "/restrito/editQuestionario.xhtml";
	}

	public String excluir(Questionario questionario) {
		this.questionarioBO.excluir(questionario);
		buscar();
		return "/restrito/listQuestionarios.xhtml";
	}	
	
	
}
