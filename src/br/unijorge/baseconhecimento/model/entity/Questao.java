/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unijorge.baseconhecimento.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author reginaldo.neto
 */
@Entity(name="Questao")
@Table(name="QUESTAO",schema="BASEDECONHECIMENTO")
@SequenceGenerator(allocationSize=0,name="SEQ_QUESTAO",sequenceName="BASEDECONHECIMENTO.SEQ_QUESTAO")
public class Questao implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -9082733208208527499L;

	@Id
	@Column(name="questao_id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="SEQ_QUESTAO")
    private Long id;
    
    @Column(name="ENUNCIADO")
    private String enunciado;
    
    @OneToMany(mappedBy ="questao",orphanRemoval=true)
    private List<Alternativa> alternativas;
        
    @ManyToOne
    @JoinColumn(name="alternativa_certa_id")
    private Alternativa alternativaCerta;

    @ManyToOne
    @JoinColumn(name="questionario_id")
    private Questionario questionario;

    public List<Alternativa> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Alternativa getAlternativaCerta() {
        return alternativaCerta;
    }

    public void setAlternativaCerta(Alternativa alternativaCerta) {
        this.alternativaCerta = alternativaCerta;
    }

    public Questionario getQuestionario() {
        return questionario;
    }

    public void setQuestionario(Questionario questionario) {
        this.questionario = questionario;
    }
    
    /* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((enunciado == null) ? 0 : enunciado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Questao)) {
			return false;
		}
		Questao other = (Questao) obj;
		if (enunciado == null) {
			if (other.enunciado != null) {
				return false;
			}
		} else if (!enunciado.equals(other.enunciado)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return this.enunciado;
	}
}
