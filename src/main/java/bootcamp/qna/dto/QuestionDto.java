package bootcamp.qna.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class QuestionDto {
	private Long id;
	@NotBlank
	private String text;
	
	private Long quizId;
	@Size(min=4, max=4)
	private List<AnswerOptionDto> answerOptions;

	public Long getQuizId() {
		return quizId;
	}

	public void setQuizId(Long quizId) {
		this.quizId = quizId;
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

	public List<AnswerOptionDto> getAnswerOptions() {
		return answerOptions;
	}

	public void setAnswerOptions(List<AnswerOptionDto> answerOptions) {
		this.answerOptions = answerOptions;
	}
	
}
