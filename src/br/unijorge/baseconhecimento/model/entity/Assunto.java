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
@Entity(name="Assunto")
@Table(name="ASSUNTO",schema="BASEDECONHECIMENTO")
@SequenceGenerator(name="SEQ_ASSUNTO",allocationSize=0,sequenceName="BASEDECONHECIMENTO.SEQ_ASSUNTO")
public class Assunto implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7318893796743293323L;

	@Id
    @GeneratedValue(generator="SEQ_ASSUNTO",strategy= GenerationType.SEQUENCE)
    private Long id;
    
    @Column(name="DESCRICAO")
    private String descricao;
    
    @OneToMany(mappedBy="assunto")
    List<Topico> topicos;

    @OneToMany(mappedBy="assunto",cascade=CascadeType.ALL) 
    List<Questionario> questionarios;
    
    @ManyToOne
    @ForeignKey(name="DISCIPLINA_ID")
    Disciplina disciplina;
    
    
    
    public Assunto() {
    	this.disciplina = new Disciplina();
	}

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

    public List<Topico> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<Topico> topicos) {
        this.topicos = topicos;
    }

    public List<Questionario> getQuestionarios() {
        return questionarios;
    }

    public void setQuestionarios(List<Questionario> questionarios) {
        this.questionarios = questionarios;
    }
    
    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
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
		if (!(obj instanceof Assunto)) {
			return false;
		}
		Assunto other = (Assunto) obj;
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
