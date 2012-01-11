package br.unijorge.baseconhecimento.view.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.unijorge.baseconhecimento.controller.business.impl.AssuntoBO;
import br.unijorge.baseconhecimento.controller.business.impl.DisciplinaBO;
import br.unijorge.baseconhecimento.model.entity.Assunto;
import br.unijorge.baseconhecimento.model.entity.Disciplina;
import br.unijorge.baseconhecimento.model.entity.Topico;
import br.unijorge.baseconhecimento.util.HandlerCarateres;
import br.unijorge.baseconhecimento.util.HandlerPastas;
import br.unijorge.baseconhecimento.util.FacesUtil;

@SessionScoped
@ManagedBean(name = "assuntoBean")
public class AssuntoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1672572816997024250L;

	private List<Assunto> assuntos;

	private AssuntoBO assuntoBO;

	private DisciplinaBO disciplinaBO;

	private Assunto assunto;

	private Long id_disciplina;

	private Long id_disciplina_antiga;

	private Disciplina disciplina;

	private List<Topico> topicos;

	private Topico topico;

	private String antigaDescricao;

	private boolean salvou;

	public AssuntoBean() {
		this.assuntoBO = new AssuntoBO();
		this.assunto = new Assunto();
		this.disciplinaBO = new DisciplinaBO();
		this.disciplina = new Disciplina();
	}

	/**
	 * @return the assuntos
	 */
	public List<Assunto> getAssuntos() {
		// Pra melhorar só verificando se houve alterações no banco
		this.assuntos = this.assuntoBO.listar();
		return assuntos;
	}

	/**
	 * @param assuntos
	 *            the assuntos to set
	 */
	public void setAssuntos(List<Assunto> assuntos) {
		this.assuntos = assuntos;
	}

	public String novoAssunto() {
		this.assunto = new Assunto();
		return "/restrito/newAssunto.xhtml";
	}

	/**
	 * @return the assunto
	 */
	public Assunto getAssunto() {
		return assunto;
	}

	/**
	 * @param assunto
	 *            the assunto to set
	 */
	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
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

	/**
	 * @return the topicos
	 */
	public List<Topico> getTopicos() {
		return topicos;
	}

	/**
	 * @param topicos
	 *            the topicos to set
	 */
	public void setTopicos(List<Topico> topicos) {
		this.topicos = topicos;
	}

	/**
	 * @return the topico
	 */
	public Topico getTopico() {
		return topico;
	}

	/**
	 * @param topico
	 *            the topico to set
	 */
	public void setTopico(Topico topico) {
		this.topico = topico;
	}

	public void buscar() {
		if ((this.id_disciplina == null || this.id_disciplina == 0)
				&& (this.assunto.getDescricao().equals("") || this.assunto
						.getDescricao() == null)) {
			this.assuntos = this.assuntoBO.listar();
		} else {
			this.assunto.getDisciplina().setId(this.id_disciplina);
			this.assuntos = this.assuntoBO.listarPorFiltro(this.assunto);
		}
	}

	/**
	 * @return the id_disciplina
	 */
	public Long getId_disciplina() {
		return id_disciplina;
	}

	/**
	 * @param id_disciplina
	 *            the id_disciplina to set
	 */
	public void setId_disciplina(Long id_disciplina) {
		this.id_disciplina = id_disciplina;
	}

	public String editar(Long id) {
		this.assunto = assuntoBO.obter(id);
		this.id_disciplina = this.assunto.getDisciplina().getId();
		this.id_disciplina_antiga = this.assunto.getDisciplina().getId();
		this.setAntigaDescricao(assunto.getDescricao());
		return "/restrito/editAssunto.xhtml";
	}

	public String excluir(Assunto assunto) {
		this.assuntoBO.excluir(assunto);
		HandlerPastas.excluirPastaAssunto(HandlerCarateres.SubstituirCaracteres(assunto.getDescricao()));
		return "/restrito/listAssuntos.xhtml";
	}

	public String salvarAssunto() {
		if (this.assunto.getDescricao().equals("")
				|| this.assunto.getDescricao() == null) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Salvar assunto.", "Descrição é um campo requerido");
			return "/restrito/newAssunto.xhtml";
		} else if (this.id_disciplina == null) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Salvar assunto.", "Disciplina é um campo requerido");
			return "/restrito/newAssunto.xhtml";
		} else {
			this.disciplina = this.disciplinaBO.obter(id_disciplina);
			this.assunto.setDisciplina(this.disciplina);

			HandlerPastas.criarPastasAssunto(HandlerCarateres.SubstituirCaracteres(this.assunto.getDescricao()));
			this.salvou = this.assuntoBO.save(this.assunto);
			this.limpaCampos();
			return this.salvou == true ? "/restrito/listAssuntos.xhtml"
					: "/restrito/newAssunto.xhtml";
		}
	}

	public String editarAssunto() {
		if (this.assunto.getDescricao().equals("")
				|| this.assunto.getDescricao() == null) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Salvar assunto.", "Descrição é um campo requerido");
			return "/restrito/newAssunto.xhtml";
		} else if (this.id_disciplina == null) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Salvar assunto.", "Disciplina é um campo requerido");
			return "/restrito/newAssunto.xhtml";
		} else {
			this.assunto.setDisciplina(this.disciplinaBO
					.obter(this.id_disciplina));

			if (!this.antigaDescricao.equals(this.assunto.getDescricao())) {
				HandlerPastas.renomearPastaAssunto(HandlerCarateres.SubstituirCaracteres(antigaDescricao),
						HandlerCarateres.SubstituirCaracteres(this.assunto.getDescricao()));
			}

			this.salvou = this.assuntoBO.edit(this.assunto);
			this.limpaCampos();
			return this.salvou == true ? "/restrito/listAssuntos.xhtml"
					: "/restrito/newAssunto.xhtml";
		}
	}

	public void limpaCampos() {
		this.id_disciplina = (long) 0;
		this.disciplina = new Disciplina();
		this.assunto = new Assunto();
	}

	public boolean isSalvou() {
		return salvou;
	}

	public void setSalvou(boolean salvou) {
		this.salvou = salvou;
	}

	public Long getId_disciplina_antiga() {
		return id_disciplina_antiga;
	}

	public void setId_disciplina_antiga(Long id_disciplina_antiga) {
		this.id_disciplina_antiga = id_disciplina_antiga;
	}

	public String getAntigaDescricao() {
		return antigaDescricao;
	}

	public void setAntigaDescricao(String antigaDescricao) {
		this.antigaDescricao = antigaDescricao;
	}
}
