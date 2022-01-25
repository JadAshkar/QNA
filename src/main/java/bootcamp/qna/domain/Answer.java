package bootcamp.qna.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String personName;
	
	@ManyToOne
	private QuestionEvent questionEvent;
	
	@ManyToOne
	private AnswerOption answerOption;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public QuestionEvent getQuestionEvent() {
		return questionEvent;
	}

	public void setQuestionEvent(QuestionEvent questionEvent) {
		this.questionEvent = questionEvent;
	}

	public AnswerOption getAnswerOption() {
		return answerOption;
	}

	public void setAnswerOption(AnswerOption answerOption) {
		this.answerOption = answerOption;
	}
}
