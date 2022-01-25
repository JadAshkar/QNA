package bootcamp.qna.dto;

import java.util.List;

public class QuizWithQuestionsDto extends QuizDto {
	private List<QuestionDto> questions;

	public List<QuestionDto> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionDto> questions) {
		this.questions = questions;
	}
}
