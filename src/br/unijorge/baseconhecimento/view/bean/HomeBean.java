package br.unijorge.baseconhecimento.view.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.unijorge.baseconhecimento.controller.business.impl.AssuntoBO;
import br.unijorge.baseconhecimento.model.entity.Assunto;
import br.unijorge.baseconhecimento.model.entity.Topico;
import br.unijorge.baseconhecimento.util.FacesUtil;
import br.unijorge.baseconhecimento.util.HandlerCarateres;

@SessionScoped
@ManagedBean(name = "homeBean")
public class HomeBean implements Serializable {

	private List<Assunto> assuntos;
	private Long id_assunto;
	private AssuntoBO assuntoBO;	
	private String caminhoPdf;
	
	public HomeBean() {
		this.assuntoBO = new AssuntoBO();
		this.caminhoPdf = "";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1872907696294504124L;

	public String areaAdministrativa() {
		return "/restrito/homeAdministrativa.xhtml?faces-redirect=true";
	}

	public String listTopicos() {
		return "/restrito/listTopicos.xhtml?faces-redirect=true";
	}
	
	public String paginaInicial() {
		return "/index.xhtml?faces-redirect=true";
	}

	public String listDisciplinas() {
		return "/restrito/listDisciplinas.xhtml?faces-redirect=true";
	}

	public String listAssuntos() {
		return "/restrito/listAssuntos.xhtml?faces-redirect=true";
	}

	public String listQuestionarios() {
		return "/restrito/listQuestionarios.xhtml?faces-redirect=true";
	}
	
	public String listQuestoes() {
		return "/restrito/listQuestoes.xhtml?faces-redirect=true";
	}
	
	public String calculadora() {
		return "/calculadoraIp.xhtml?faces-redirect=true";
	}
	
	public String creditos(){
		return "/creditos.xhtml?faces-redirect=true";
	}
	
	public String questionario(){
		if (this.id_assunto != null && this.id_assunto != 0){
			ConsultaQuestionarioBean consultaQuestionarioBean = (ConsultaQuestionarioBean)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("consultaQuestionarioBean");
			if (consultaQuestionarioBean == null){
				consultaQuestionarioBean = new ConsultaQuestionarioBean();
				consultaQuestionarioBean.setAssunto(new AssuntoBO().obter(this.id_assunto));
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("consultaQuestionarioBean", consultaQuestionarioBean);
			} else {
				consultaQuestionarioBean.setAssunto(new AssuntoBO().obter(this.id_assunto));
			}
			if(consultaQuestionarioBean.montaQuestionario()){
				return "/consultaQuestionario.xhtml";
			}else{
				FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
						"Questionário", "Não há questionário para este assunto.");
				return "";
			}
			
		} else {
			return null;
		}
	}

	/**
	 * @return the caminhoPdf
	 */
	public String getCaminhoPdf() {
		return caminhoPdf;
	}

	/**
	 * @param caminhoPdf
	 *            the caminhoPdf to set
	 */
	public void setCaminhoPdf(String caminhoPdf) {
		this.caminhoPdf = caminhoPdf;
	}

	/**
	 * @return the assuntos
	 */
	public List<Assunto> getAssuntos() {
		assuntos = assuntoBO.listar();
		return assuntos;
	}

	/**
	 * @param assuntos
	 *            the assuntos to set
	 */
	public void setAssuntos(List<Assunto> assuntos) {
		this.assuntos = assuntos;
	}

	public Long getId_assunto() {
		return id_assunto;
	}

	public void setId_assunto(Long id_assunto) {
		this.id_assunto = id_assunto;
	}

	public void carregarConteudo(Topico topico) {
		this.caminhoPdf = "/BaseConhecimento/conteudos/assuntos/"
				+ HandlerCarateres.SubstituirCaracteres(topico.getAssunto().getDescricao().toLowerCase()
						.replace(" ", "_")) + HandlerCarateres.SubstituirCaracteres("/topicos/"
				+ topico.getDescricao().toLowerCase().replace(" ", "_"))+ "/"
				+ HandlerCarateres.SubstituirCaracteres(topico.getArquivo().toLowerCase().replace(" ", "_"));
		
		this.id_assunto = topico.getAssunto().getId();
	}	
	
}
