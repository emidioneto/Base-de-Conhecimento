package br.unijorge.baseconhecimento.view.bean;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.FileUploadEvent;

import br.unijorge.baseconhecimento.controller.business.impl.AssuntoBO;
import br.unijorge.baseconhecimento.controller.business.impl.TopicoBO;
import br.unijorge.baseconhecimento.model.entity.Assunto;
import br.unijorge.baseconhecimento.model.entity.Topico;
import br.unijorge.baseconhecimento.util.HandlerCarateres;
import br.unijorge.baseconhecimento.util.HandlerPastas;
import br.unijorge.baseconhecimento.util.FacesUtil;

@SessionScoped
@ManagedBean(name = "topicoBean")
public class TopicoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6662304955899989375L;

	private List<Topico> topicos;
	private TopicoBO topicoBO;
	private Topico topico;
	private String nome_arquivo = "";
	private String nome_arquivo_antigo = "";
	private String topicoAntigo = "";
	private FileUploadController fileUpload;
	private AssuntoBO assuntoBO;
	private boolean salvou;

	private Long id_disciplina;
	private Long id_assunto;
	private Long id_assunto_antigo;
	private String descricao_assunto_antigo;

	private List<SelectItem> listaTopicos;

	private List<SelectItem> listaAssuntos;

	private SecureRandom random;

	public TopicoBean() {
		this.topicoBO = new TopicoBO();
		this.assuntoBO = new AssuntoBO();
		this.topico = new Topico();
		fileUpload = new FileUploadController();
		this.random = new SecureRandom();
	}

	/**
	 * @return the topicos
	 */
	public List<Topico> getTopicos() {
		// Pra melhorar só verificando se houve alterações no banco
		this.topicos = this.topicoBO.listar();
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

	/**
	 * @return the listaTopicos
	 */
	public List<SelectItem> getListaTopicos() {
		return listaTopicos;
	}

	/**
	 * @param listaTopicos
	 *            the listaTopicos to set
	 */
	public void setListaTopicos(List<SelectItem> listaTopicos) {
		this.listaTopicos = listaTopicos;
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

	/**
	 * @return the id_assunto
	 */
	public Long getId_assunto() {
		return id_assunto;
	}

	/**
	 * @param id_assunto
	 *            the id_assunto to set
	 */
	public void setId_assunto(Long id_assunto) {
		this.id_assunto = id_assunto;
	}

	/**
	 * @return the listaAssuntos
	 */
	public List<SelectItem> getListaAssuntos() {

		List<Assunto> listAssuntos;
		this.listaAssuntos = new ArrayList<SelectItem>();
		listAssuntos = assuntoBO.listar();
		for (Assunto assunto : listAssuntos) {
			Assunto a = assunto;
			this.listaAssuntos.add(new SelectItem(a.getId(), a.getDescricao()));
		}

		return listaAssuntos;
	}

	/**
	 * @param listaAssuntos
	 *            the listaAssuntos to set
	 */
	public void setListaAssuntos(List<SelectItem> listaAssuntos) {
		this.listaAssuntos = listaAssuntos;
	}

	public String excluir(Topico topico) {
		this.topicoBO.excluir(topico);
		HandlerPastas.apagarArquivoDoTopico(HandlerCarateres.SubstituirCaracteres(topico.getAssunto().getDescricao()),
				HandlerCarateres.SubstituirCaracteres(topico.getDescricao()), HandlerCarateres.SubstituirCaracteres(topico.getArquivo()));
		return "/restrito/listTopicos.xhtml";
	}

	public String salvarTopico() {
		if (this.topico.getDescricao().equals("")
				|| this.topico.getDescricao() == null) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Salvar tópico.", "Descrição é um campo requerido");
			return "/restrito/newTopico.xhtml";
		} else if (this.id_assunto == null) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Salvar tópico.", "Assunto é um campo requerido");
			return "/restrito/newTopico.xhtml";
		} else if (this.topico.getArquivo().equals("")
				|| this.topico.getArquivo() == null) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Editar tópico.", "Arquivo é um campo requerido");
			return "/restrito/newTopico.xhtml";
		} else {
			this.topico.setAssunto(this.assuntoBO.obter(id_assunto));
			HandlerPastas.criarPastasTopico(HandlerCarateres.SubstituirCaracteres(topico.getAssunto().getDescricao()),
					HandlerCarateres.SubstituirCaracteres(topico.getDescricao()));
			HandlerPastas.moverArquivoDoTemp(
					HandlerCarateres.SubstituirCaracteres(this.topico.getAssunto().getDescricao()), 
					HandlerCarateres.SubstituirCaracteres(this.topico.getDescricao()), 
					HandlerCarateres.SubstituirCaracteres(this.topico.getArquivo()));
			this.salvou = this.topicoBO.save(this.topico);
			this.limpaCampos();
			return this.salvou == true ? "/restrito/listTopicos.xhtml"
					: "/restrito/newTopico.xhtml";
		}
	}

	public String editarTopico() {
		if (this.topico.getDescricao().equals("")
				|| this.topico.getDescricao() == null) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Editar tópico.", "Descrição é um campo requerido");
			return "/restrito/newTopico.xhtml";
		} else if (this.id_assunto == null) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Editar tópico.", "Assunto é um campo requerido");
			return "/restrito/newTopico.xhtml";
		} else if (this.topico.getArquivo().equals("")
				|| this.topico.getArquivo() == null) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Editar tópico.", "Arquivo é um campo requerido");
			return "/restrito/newTopico.xhtml";
		} else {
			this.topico.setAssunto(this.assuntoBO.obter(this.id_assunto));			

			if (!this.topico.getDescricao().equals(this.topicoAntigo)) {
				HandlerPastas.renomearPastaTopico(
						HandlerCarateres.SubstituirCaracteres(topico.getAssunto().getDescricao()), 
						HandlerCarateres.SubstituirCaracteres(this.topicoAntigo), HandlerCarateres.SubstituirCaracteres(topico.getDescricao()));
			}
			// Se estiver trocando de arquivo
			if (!this.topico.getArquivo().equals(this.nome_arquivo_antigo)) {
				HandlerPastas.moverArquivoDoTemp(
						HandlerCarateres.SubstituirCaracteres(this.topico.getAssunto().getDescricao()), 
						HandlerCarateres.SubstituirCaracteres(this.topico.getDescricao()),
						HandlerCarateres.SubstituirCaracteres(this.topico.getArquivo()));
				// excluo o arquivo antigo
				HandlerPastas.apagarArquivoDoTopico(
						HandlerCarateres.SubstituirCaracteres(this.topico.getAssunto().getDescricao()), 
						HandlerCarateres.SubstituirCaracteres(this.topico.getDescricao()),
						HandlerCarateres.SubstituirCaracteres(this.nome_arquivo_antigo));
			}

			if (this.id_assunto != this.id_assunto_antigo) {
				HandlerPastas.moverTopico(HandlerCarateres.SubstituirCaracteres(this.descricao_assunto_antigo),
						HandlerCarateres.SubstituirCaracteres(this.topico.getAssunto().getDescricao().toLowerCase()
								.replace(" ", "")), HandlerCarateres.SubstituirCaracteres(topico.getDescricao()
								.toLowerCase().replace(" ", "")));
			}
			
			this.salvou = this.topicoBO.edit(this.topico);
			
			this.limpaCampos();
			return this.salvou == true ? "/restrito/listTopicos.xhtml"
					: "/restrito/editTopico.xhtml";
		}
	}

	public String editar(Long id) {
		this.topico = topicoBO.obter(id);
		this.id_assunto = topico.getAssunto().getId();
		this.id_assunto_antigo = topico.getAssunto().getId();
		this.setDescricao_assunto_antigo(topico.getAssunto().getDescricao());
		this.topicoAntigo = topico.getDescricao();
		this.nome_arquivo_antigo = topico.getArquivo();
		return "/restrito/editTopico.xhtml";
	}

	public void buscar() {
		if ((this.id_assunto == null || this.id_assunto == 0)
				&& (this.topico.getDescricao().equals("") || this.topico
						.getDescricao() == null)) {
			this.topicos = this.topicoBO.listar();
		} else {
			this.topico.setAssunto(new Assunto());
			this.topico.getAssunto().setId(this.id_assunto);
			this.topicos = this.topicoBO.listarPorFiltro(this.topico);
		}
		this.limpaCamposAposBusca();

	}

	public void limpaCampos() {
		this.id_assunto = (long) 0;
		this.topico = new Topico();
	}

	public String novoTopico() {
		this.topico = new Topico();
		return "/restrito/newTopico.xhtml";
	}

	public void limpaCamposAposBusca() {
		this.id_assunto = (long) 0;
		this.topico = new Topico();
	}

	public void submitArquivo(FileUploadEvent event)
			throws FileNotFoundException, IOException {

		nome_arquivo = new BigInteger(60, this.random).toString(32)
				+ event.getFile().getFileName();

		if (nome_arquivo.substring(nome_arquivo.lastIndexOf('.') + 1)
				.toLowerCase().equals("pdf")) {
			this.fileUpload.carregarArquivo(event, HandlerCarateres.SubstituirCaracteres(nome_arquivo));
			this.topico
					.setArquivo(HandlerCarateres.SubstituirCaracteres(nome_arquivo.toLowerCase().replace(" ", "_")));
		} else {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Adicionar arquivo", "Extensão não permitida");
		}
	}

	public String getTopicoAntigo() {
		return topicoAntigo;
	}

	public void setTopicoAntigo(String topicoAntigo) {
		this.topicoAntigo = topicoAntigo;
	}

	public Long getId_assunto_antigo() {
		return id_assunto_antigo;
	}

	public void setId_assunto_antigo(Long id_assunto_antigo) {
		this.id_assunto_antigo = id_assunto_antigo;
	}

	public String getDescricao_assunto_antigo() {
		return descricao_assunto_antigo;
	}

	public void setDescricao_assunto_antigo(String descricao_assunto_antigo) {
		this.descricao_assunto_antigo = descricao_assunto_antigo;
	}
}
