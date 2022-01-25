package bootcamp.qna.dto;

public class AnswerGroupDto {
	private Long answerOptionId;
	private int answerOptionOrder;
	private int totalAnswers;
	public Long getAnswerOptionId() {
		return answerOptionId;
	}
	public void setAnswerOptionId(Long answerOptionId) {
		this.answerOptionId = answerOptionId;
	}
	public int getAnswerOptionOrder() {
		return answerOptionOrder;
	}
	public void setAnswerOptionOrder(int answerOptionOrder) {
		this.answerOptionOrder = answerOptionOrder;
	}
	public int getTotalAnswers() {
		return totalAnswers;
	}
	public void setTotalAnswers(int totalAnswers) {
		this.totalAnswers = totalAnswers;
	}
	
	
}
