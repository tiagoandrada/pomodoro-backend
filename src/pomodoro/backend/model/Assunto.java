package pomodoro.backend.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "assunto")
public class Assunto {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@ManyToOne
	@JoinColumn(name="materia_id")
	private Materia materia;
	private String nome;
	
	@OneToMany(mappedBy="assunto", targetEntity = FlashCard.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private transient List<FlashCard> flashcards;
	
	@OneToMany(mappedBy="assunto", targetEntity = Ficha.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private transient List<Ficha> fichas;
	
	public Assunto(){}
	
	public Assunto(Materia materia, String assunto){
		this.materia = materia;
		this.nome = assunto;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<FlashCard> getFlashcards() {
		return flashcards;
	}

	public void setFlashcards(List<FlashCard> flashcards) {
		this.flashcards = flashcards;
	}
	
	public long getId(){
		return this.id;
	}
	
}