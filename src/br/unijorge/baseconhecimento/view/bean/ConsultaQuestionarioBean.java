package br.unijorge.baseconhecimento.view.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.unijorge.baseconhecimento.controller.business.impl.AlternativaBO;
import br.unijorge.baseconhecimento.controller.business.impl.QuestionarioBO;
import br.unijorge.baseconhecimento.model.entity.Alternativa;
import br.unijorge.baseconhecimento.model.entity.Assunto;
import br.unijorge.baseconhecimento.model.entity.Questao;
import br.unijorge.baseconhecimento.model.entity.Questionario;

@SessionScoped
@ManagedBean(name="consultaQuestionarioBean")
public class ConsultaQuestionarioBean  implements Serializable{

	private static final long serialVersionUID = -2444205513813156467L;

	private Assunto assunto;
	private List<Questionario> questionarios;
	private Questionario questionario;
	private HashMap<Integer, Questao> questoes;
	private HashMap<Integer, Questao> gabarito;
	private String resultado;
	
	public ConsultaQuestionarioBean() {
		this.setQuestionarios(new ArrayList<Questionario>());
		this.setQuestoes(new HashMap<Integer, Questao>());
		this.setGabarito(new HashMap<Integer, Questao>());
		this.setResultado(null);
	}
		
	public boolean montaQuestionario(){
		if (this.assunto != null){
			this.setGabarito(new HashMap<Integer, Questao>());
			this.setResultado(null);
			questionarios = new QuestionarioBO().listarPorAssunto(this.assunto);
			if(questionarios.size() != 0){
				Collections.shuffle(questionarios);
				questionario = questionarios.iterator().next();
				List<Questao> listQuestoes = questionario.getQuestoes();
				Collections.shuffle(listQuestoes);
				int contQuestao = 1;
				for (Questao q : listQuestoes){
					try {
						Questao gab = new Questao();
						gab.setAlternativaCerta(new AlternativaBO().obter(q.getAlternativaCerta().getId()));
						gabarito.put(Integer.valueOf(contQuestao), gab);
						
						q.setAlternativaCerta(new Alternativa());
						questoes.put(Integer.valueOf(contQuestao), q);
						Collections.shuffle(q.getAlternativas());
						contQuestao++;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return true;
			}else{
				return false;
			}			
		}
		return false;
	}
	public String verificaRespostas(){
		int contCerta = 0;
		int contErrada = 0;
		for (Integer key : questoes.keySet()){
			Questao q = questoes.get(key);
			q.setAlternativaCerta(new AlternativaBO().obter(q.getAlternativaCerta().getId()));
			if (gabarito.get(key).getAlternativaCerta().getId().equals(questoes.get(key).getAlternativaCerta().getId())){
				contCerta++;
			} else {
				contErrada++;
			}
			this.resultado = "Você acertou " + contCerta + " de "+ gabarito.size() + "!";
			
		}
		System.out.println("Certo: "+contCerta);
		System.out.println("Errado: "+contErrada);
		return "/consultaQuestionarioRespostas.xhtml";
	}
	public String retornaRespostaCorreta(Integer numQuestao){
		Questao q = gabarito.get(numQuestao);
		if (q != null){
			return q.getAlternativaCerta().getDescricao();
		} else {
			return "";
		}
	}


	public Assunto getAssunto() {
		return assunto;
	}

	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
	}

	public List<Questionario> getQuestionarios() {
		return questionarios;
	}


	public void setQuestionarios(List<Questionario> questionarios) {
		this.questionarios = questionarios;
	}

	public Questionario getQuestionario() {
		return questionario;
	}

	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}

	public HashMap<Integer, Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(HashMap<Integer, Questao> questoes) {
		this.questoes = questoes;
	}

	public HashMap<Integer, Questao> getGabarito() {
		return gabarito;
	}

	public void setGabarito(HashMap<Integer, Questao> gabarito) {
		this.gabarito = gabarito;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

}
