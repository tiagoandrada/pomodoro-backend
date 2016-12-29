package pomodoro.backend.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="flashcard")
public class FlashCard {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column(length=5000)
	private String frontContent;
	@Column(length=5000)
	private String backContent;
	
	@ManyToOne
	@JoinColumn(name="assunto_id")
	private Assunto assunto;
	
	public FlashCard(){}
	
	public FlashCard(Assunto assunto, String frontContent, String backContent){
		this.assunto = assunto;
		this.frontContent = frontContent;
		this.backContent = backContent;
	}

	public String getFrontContent() {
		return frontContent;
	}

	public void setFrontContent(String frontContent) {
		this.frontContent = frontContent;
	}

	public String getBackContent() {
		return backContent;
	}

	public void setBackContent(String backContent) {
		this.backContent = backContent;
	}
	
}
