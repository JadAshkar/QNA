package bootcamp.qna.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class QuestionEvent {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private boolean processed;
	
	@ManyToOne
	private Question question;
	
	@ManyToOne
	private Event event;
	
	@OneToMany(mappedBy = "questionEvent")
	private Set<Answer> answers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}
