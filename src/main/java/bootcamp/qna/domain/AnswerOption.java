package bootcamp.qna.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class AnswerOption {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String text;
	private boolean correct;
	private int optionOrder;
	
	@ManyToOne
	private Question question;
	
	@OneToMany(mappedBy = "answerOption")
	private Set<Answer> answers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public int getOptionOrder() {
		return optionOrder;
	}

	public void setOptionOrder(int optionOrder) {
		this.optionOrder = optionOrder;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
}
