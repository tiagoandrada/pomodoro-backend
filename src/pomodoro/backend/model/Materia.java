package pomodoro.backend.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="materia")
public class Materia {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String nome;
	
	@OneToMany(mappedBy="materia", targetEntity=Assunto.class, fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private transient List<Assunto> assuntos;
	
	public Materia(){}
	
	public Materia(String nome){
		this.nome = nome;
	}
		
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Assunto> getAssuntos() {
		return assuntos;
	}

	public void setAssuntos(List<Assunto> assuntos) {
		this.assuntos = assuntos;
	}

}


