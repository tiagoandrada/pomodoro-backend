package pomodoro.backend.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ficha")
public class Ficha {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String content;
	
	@ManyToOne
	@JoinColumn(name="assunto_id")
	private Assunto assunto;
	
	public Ficha(){}
	
	public Ficha(Assunto assunto, String content){
		this.assunto = assunto;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Assunto getAssunto() {
		return assunto;
	}

	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
	}
	
}


