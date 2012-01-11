/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unijorge.baseconhecimento.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author reginaldo.neto
 */
@Entity(name="Alternativa")
@Table(name="ALTERNATIVA",schema="BASEDECONHECIMENTO")
@SequenceGenerator(allocationSize=0,name="SEQ_ALTERNATIVA",sequenceName="BASEDECONHECIMENTO.SEQ_ALTERNATIVA")
public class Alternativa implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8797388532427402618L;

	@Id
    @GeneratedValue(generator="SEQ_ALTERNATIVA",strategy= GenerationType.SEQUENCE)
    private Long id;
    
    @Column(name="DESCRICAO")
    private String descricao;
    
    @ManyToOne
    @JoinColumn(name="questao_id")
    private Questao questao;
    

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
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
		if (!(obj instanceof Alternativa)) {
			return false;
		}
		Alternativa other = (Alternativa) obj;
		if (descricao == null) {
			if (other.descricao != null) {
				return false;
			}
		} else if (!descricao.equals(other.descricao)) {
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.descricao;
	}
	
	
}
