package br.unijorge.baseconhecimento.view.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name="administracaoBean")
public class AdministracaoBean implements Serializable{
    
	private static final long serialVersionUID = 1872907696294504124L;

	public String areaAdministrativa(){
		return "/restrito/homeAdministrativa.xhtml?faces-redirect=true";
	}
	
	public String listTopicos(){
		return "/restrito/listTopicos.xhtml?faces-redirect=true";
	}

	public String listDisciplinas(){
		return "/restrito/listDisciplinas.xhtml?faces-redirect=true";
	}
	
	public String listAssuntos(){
		return "/restrito/listAssuntos.xhtml?faces-redirect=true";
	}
	
	public String listQuestionarios(){
		return "/restrito/listQuestionarios.xhtml?faces-redirect=true";
	}
	
	public String listQuestoes(){
		return "/restrito/listQuestoes.xhtml?faces-redirect=true";
	}
	
}
