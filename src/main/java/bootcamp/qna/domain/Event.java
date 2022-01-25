package bootcamp.qna.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String accessCode;
	private String secretCode;
	private boolean ended;
	
	@ManyToOne
	private Quiz quiz;
	
	@OneToMany(mappedBy = "event")
	private Set<QuestionEvent> questionEvents;
	
	public Set<QuestionEvent> getQuestionEvents() {
		return questionEvents;
	}
	public void setQuestionEvents(Set<QuestionEvent> questionEvents) {
		this.questionEvents = questionEvents;
	}
	public Quiz getQuiz() {
		return quiz;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccessCode() {
		return accessCode;
	}
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	public String getSecretCode() {
		return secretCode;
	}
	public void setSecretCode(String secretCode) {
		this.secretCode = secretCode;
	}
	public boolean isEnded() {
		return ended;
	}
	public void setEnded(boolean ended) {
		this.ended = ended;
	}
}
