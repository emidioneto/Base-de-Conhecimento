/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unijorge.baseconhecimento.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author reginaldo.neto
 */
@Entity(name="Questionario")
@Table(name="QUESTIONARIO",schema="BASEDECONHECIMENTO")
@SequenceGenerator(allocationSize=0,name="SEQ_QUESTIONARIO",sequenceName="BASEDECONHECIMENTO.SEQ_QUESTIONARIO")
public class Questionario implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1843291307064257315L;

	@Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="SEQ_QUESTIONARIO")
    private Long id;
    
    @Column(name="DESCRICAO")
    private String descricao;
    
    @OneToMany(mappedBy="questionario")
    private List<Questao> questoes;
    
    @ManyToOne(cascade=CascadeType.REFRESH)
    @ForeignKey(name="FK_IDASSUNTO")
    private Assunto assunto;

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

    public List<Questao> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<Questao> questoes) {
        this.questoes = questoes;
    }

    public Assunto getAssunto() {
        return assunto;
    }

    public void setAssunto(Assunto assunto) {
        this.assunto = assunto;
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
		if (!(obj instanceof Questionario)) {
			return false;
		}
		Questionario other = (Questionario) obj;
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

	@Override
	public String toString() {
		return this.descricao;
	}

}
