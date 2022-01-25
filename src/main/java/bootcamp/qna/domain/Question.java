package bootcamp.qna.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String text;
	
	@ManyToOne
	private Quiz quiz;
	
	@OneToMany(mappedBy = "question", cascade = CascadeType.PERSIST)
	private List<AnswerOption> answerOptions;
	
	@OneToMany(mappedBy = "question")
	private Set<QuestionEvent> questionEvents;

	public Set<QuestionEvent> getQuestionEvents() {
		return questionEvents;
	}

	public void setQuestionEvents(Set<QuestionEvent> questionEvents) {
		this.questionEvents = questionEvents;
	}

	public List<AnswerOption> getAnswerOptions() {
		return answerOptions;
	}

	public void setAnswerOptions(List<AnswerOption> answerOptions) {
		this.answerOptions = answerOptions;
	}

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

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	
}
