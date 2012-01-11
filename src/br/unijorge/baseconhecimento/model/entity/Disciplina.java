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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author reginaldo.neto
 */
@Entity(name="Disciplina")
@Table(name="DISCIPLINA",schema="BASEDECONHECIMENTO")
@SequenceGenerator(name="SEQ_DISCIPLINA",sequenceName="BASEDECONHECIMENTO.SEQ_DISCIPLINA",allocationSize=0)
public class Disciplina implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1158209471038571201L;

	@Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="SEQ_DISCIPLINA")
    private Long id;
    
    @Column(name="NOME")
    private String nome;

    @OneToMany(mappedBy="disciplina")
    List<Assunto> assuntos;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		if (!(obj instanceof Disciplina)) {
			return false;
		}
		Disciplina other = (Disciplina) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return this.nome;
	}
    
}
