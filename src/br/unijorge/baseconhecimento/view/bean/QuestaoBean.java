package br.unijorge.baseconhecimento.view.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.unijorge.baseconhecimento.controller.business.impl.AlternativaBO;
import br.unijorge.baseconhecimento.controller.business.impl.AssuntoBO;
import br.unijorge.baseconhecimento.controller.business.impl.QuestaoBO;
import br.unijorge.baseconhecimento.controller.business.impl.QuestionarioBO;
import br.unijorge.baseconhecimento.excessao.BusinessExceptions;
import br.unijorge.baseconhecimento.model.entity.Alternativa;
import br.unijorge.baseconhecimento.model.entity.Assunto;
import br.unijorge.baseconhecimento.model.entity.Questao;
import br.unijorge.baseconhecimento.model.entity.Questionario;
import br.unijorge.baseconhecimento.persistence.dao.AssuntoDao;
import br.unijorge.baseconhecimento.persistence.dao.QuestionarioDao;
import br.unijorge.baseconhecimento.util.FacesUtil;

@SessionScoped
@ManagedBean(name="questaoBean")
public class QuestaoBean  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6363569762163386593L;
	
	private List<Questao> questoes;
	private QuestaoBO questaoBO;
	private Questao questao;
	private Questao questaoBusca;
	
	private Long id_assunto;
	private Long id_questionario;
	private Long id_alternativa_certa;
	private List<SelectItem> listaAssuntos;
	private List<SelectItem> listaQuestionarios;
	private List<SelectItem> listaAlternativas;
	
	public QuestaoBean() {
		this.questaoBO = new QuestaoBO();
		this.questao = new Questao();
		this.questaoBusca = new Questao();
		this.questao.setAlternativas(new ArrayList<Alternativa>());
		this.questao.setAlternativaCerta(new Alternativa());
	}
	
	public Long getId_assunto() {
		return id_assunto;
	}
	
	public void setId_assunto(Long id_assunto) {
		this.id_assunto = id_assunto;
	}
	
	public Long getId_questionario() {
		return id_questionario;
	}
	
	public void setId_questionario(Long id_questionario) {
		this.id_questionario = id_questionario;
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
	
	public List<SelectItem> getListaQuestionarios() {
		if (this.id_assunto != null && this.id_assunto != 0){
			try {
				Questionario questionario = new Questionario();
				questionario.setAssunto(new AssuntoDao().findById(id_assunto));
				
				List<Questionario> listQuestionarios = new QuestionarioDao().findbyFilter(questionario);
				this.listaQuestionarios = new ArrayList<SelectItem>();
				for (Questionario quest : listQuestionarios) {
					Questionario a = quest;
					this.listaQuestionarios.add(new SelectItem(a.getId(), a.getDescricao()));
				}
			} catch (BusinessExceptions e){
				FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_ERROR, "Listar questões", "Ocorreu um erro ao filtrar por assunto!");
			}
		} else {
			this.listaQuestionarios = new ArrayList<SelectItem>();
		}
		return listaQuestionarios;
	}
	
	public void setListaQuestionarios(List<SelectItem> listaQuestionarios) {
		this.listaQuestionarios = listaQuestionarios;
	}
	
	public Long getId_alternativa_certa() {
		if (this.questao.getAlternativaCerta() != null && this.questao.getAlternativaCerta().getId() != null && this.questao.getAlternativaCerta().getId() != 0){
			this.id_alternativa_certa = this.questao.getAlternativaCerta().getId();
		}
		return id_alternativa_certa;
	}

	public void setId_alternativa_certa(Long id_alternativa_certa) {
		this.id_alternativa_certa = id_alternativa_certa;
	}

	public List<SelectItem> getListaAlternativas() {
		ArrayList<Alternativa> listAlt = new AlternativaBO().listarPorQuestao(this.questao);
		this.listaAlternativas = new ArrayList<SelectItem>();
		for (Alternativa alt : listAlt) {
			Alternativa a = alt;
			this.listaAlternativas.add(new SelectItem(a.getId(), a.getDescricao()));
		}
		return listaAlternativas;
	}

	public void setListaAlternativas(List<SelectItem> listaAlternativas) {
		this.listaAlternativas = listaAlternativas;
	}

	
	public void carregaComboQuestionario(){
		getListaQuestionarios();
	}
	
	public List<Questao> getQuestoes() {
		return this.questoes;
	}
	
	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}
	
	public Questao getQuestao() {
		return questao;
	}
	
	public void setQuestao(Questao questao) {
		this.questao = questao;
	}
	public Questao getQuestaoBusca() {
		return questaoBusca;
	}

	public void setQuestaoBusca(Questao questaoBusca) {
		this.questaoBusca = questaoBusca;
	}
	
	public String buscar() {
		if (this.id_questionario == null || this.id_questionario == 0) {
			this.questoes = new ArrayList<Questao>();
		} else {
			this.questaoBusca.setQuestionario(new QuestionarioBO().obter(this.id_questionario));
			this.questoes = this.questaoBO.listarPorFiltro(this.questaoBusca);
		}
		return "/restrito/listQuestoes.xhtml";

	}
	public String novaQuestao(){
		this.questao = new Questao();
		this.questao.setAlternativas(new ArrayList<Alternativa>());
		this.questao.setAlternativaCerta(new Alternativa());
		return "/restrito/newQuestao.xhtml";
	}
	public String incluirQuestao() {
		if (this.id_questionario != null && this.id_questionario != 0){
			this.questao.setQuestionario(new QuestionarioBO().obter(this.id_questionario));
		}
		if (this.id_alternativa_certa != null && this.id_alternativa_certa != 0){
			this.questao.setAlternativaCerta(new AlternativaBO().obter(this.id_alternativa_certa));
		} else {
			this.questao.setAlternativaCerta(null);
		}
		return this.questaoBO.save(this.questao) == true ? "/restrito/listQuestao.xhtml" : "/restrito/newQuestao.xhtml" ;
	}
	
	public String atualizarQuestao() {
		if(this.id_questionario != this.questao.getQuestionario().getId()){
			this.questao.setQuestionario(new QuestionarioBO().obter(this.id_questionario));
		}	
		if (this.id_alternativa_certa != this.questao.getAlternativaCerta().getId()){
			this.questao.setAlternativaCerta(new AlternativaBO().obter(this.id_alternativa_certa));
		}
		this.questaoBO.edit(this.questao);
		return "/restrito/listQuestionario.xhtml";
	}
	
	public String editar(Questao questao) {
		this.questao = questao;
		if (this.questao.getAlternativaCerta() == null){
			this.questao.setAlternativaCerta(new Alternativa());
		}
		if (this.questao.getAlternativas() == null){
			this.questao.setAlternativas(new ArrayList<Alternativa>());
		}
		this.id_questionario = this.questao.getQuestionario().getId();
		return "/restrito/editQuestao.xhtml";
	}

	public String excluir(Questao questao) {
		this.questaoBO.excluir(questao);
		this.questao = new Questao();
		buscar();
		return "/restrito/listQuestao.xhtml";
	}	
	
	public void addAlternativa(){
		this.questao.getAlternativas().add(new Alternativa());
	}
	public void removeAlternativa(Alternativa a){
		if (a.getId() != null && a.getId() != 0){
			try {
				AlternativaBO abo = new AlternativaBO();
				abo.excluir(abo.obter(a.getId()));
				this.questao.getAlternativas().remove(a);
			} catch (Exception e){}
		} else {
			this.questao.getAlternativas().remove(a);
		}
		
	}	
	
	public void salvarAlternativas(){
		if (this.questao.getAlternativas() != null){
			AlternativaBO abo = new AlternativaBO();
			for (Alternativa alt : this.questao.getAlternativas()) {
				if (alt.getId() != null && alt.getId() != 0){
					Alternativa altSalva = abo.obter(alt.getId());
					if (!altSalva.getDescricao().equals(alt.getDescricao())){
						altSalva.setDescricao(alt.getDescricao());
						abo.edit(altSalva);
					}
				} else {
					Alternativa nova = new Alternativa();
					nova.setDescricao(alt.getDescricao());
					nova.setQuestao(this.questao);
					abo.save(nova);
					alt.setId(nova.getId());
				}
				
			}
		}
	}


}
