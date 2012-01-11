package br.unijorge.baseconhecimento.view.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.unijorge.baseconhecimento.controller.business.impl.CalculadoraBO;

@SessionScoped
@ManagedBean(name = "calculadoraBean")
public class CalculadoraBean  implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 684069833195755910L;
	private String oct1;
	private String oct2;
	private String oct3;
	private String oct4;
	private String mask;	
	private CalculadoraBO calculadoraBO;
	
	private String classe;
	
	private String mascaraDec;
	
	private String mascaraBin;
	
	private String ipBinario;
	
	public CalculadoraBean() {
		this.setCalculadoraBO(new CalculadoraBO());
	}
	/**
	 * @return the oct1
	 */
	public String getOct1() {
		return oct1;
	}
	/**
	 * @param oct1 the oct1 to set
	 */
	public void setOct1(String oct1) {
		this.oct1 = oct1;
	}
	/**
	 * @return the oct2
	 */
	public String getOct2() {
		return oct2;
	}
	/**
	 * @param oct2 the oct2 to set
	 */
	public void setOct2(String oct2) {
		this.oct2 = oct2;
	}
	/**
	 * @return the oct3
	 */
	public String getOct3() {
		return oct3;
	}
	/**
	 * @param oct3 the oct3 to set
	 */
	public void setOct3(String oct3) {
		this.oct3 = oct3;
	}
	/**
	 * @return the oct4
	 */
	public String getOct4() {
		return oct4;
	}
	/**
	 * @param oct4 the oct4 to set
	 */
	public void setOct4(String oct4) {
		this.oct4 = oct4;
	}
	/**
	 * @return the mask
	 */
	public String getMask() {
		return mask;
	}
	/**
	 * @param mask the mask to set
	 */
	public void setMask(String mask) {
		this.mask = mask;
	}
	public CalculadoraBO getCalculadoraBO() {
		return calculadoraBO;
	}
	public void setCalculadoraBO(CalculadoraBO calculadoraBO) {
		this.calculadoraBO = calculadoraBO;
	}
	
	/**
	 * @return the classe
	 */
	public String getClasse() {
		return classe;
	}
	/**
	 * @param classe the classe to set
	 */
	public void setClasse(String classe) {
		this.classe = classe;
	}
	/**
	 * @return the mascaraDec
	 */
	public String getMascaraDec() {
		return mascaraDec;
	}
	/**
	 * @param mascaraDec the mascaraDec to set
	 */
	public void setMascaraDec(String mascaraDec) {
		this.mascaraDec = mascaraDec;
	}
	/**
	 * @return the mascaraBin
	 */
	public String getMascaraBin() {
		return mascaraBin;
	}
	/**
	 * @param mascaraBin the mascaraBin to set
	 */
	public void setMascaraBin(String mascaraBin) {
		this.mascaraBin = mascaraBin;
	}
	/**
	 * @return the ipBinario
	 */
	public String getIpBinario() {
		return ipBinario;
	}
	/**
	 * @param ipBinario the ipBinario to set
	 */
	public void setIpBinario(String ipBinario) {
		this.ipBinario = ipBinario;
	}
	public void Calcular(){
		this.classe = calculadoraBO.classeIP( Integer.parseInt(this.oct1));
		
		this.mascaraDec = calculadoraBO.mascaraDEC(Integer.parseInt(this.mask));
		
		
		this.ipBinario = calculadoraBO.convertDecParaBin(Integer.parseInt(this.oct1))+" . ";
		
		this.ipBinario += calculadoraBO.convertDecParaBin(Integer.parseInt(this.oct2))+" . ";
		
		this.ipBinario += calculadoraBO.convertDecParaBin(Integer.parseInt(this.oct3))+" . ";
		
		this.ipBinario += calculadoraBO.convertDecParaBin(Integer.parseInt(this.oct4));
		
		this.mascaraBin = calculadoraBO.mascaraBIN(Integer.parseInt(this.mask));
	}
	
	
}
