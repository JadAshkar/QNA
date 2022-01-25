package bootcamp.qna.dto;

public class AnswerDto {
	private Long questionEventId;
	private Long answerOptionId;
	private String participantName;
	public Long getQuestionEventId() {
		return questionEventId;
	}
	public void setQuestionEventId(Long questionEventId) {
		this.questionEventId = questionEventId;
	}
	public Long getAnswerOptionId() {
		return answerOptionId;
	}
	public void setAnswerOptionId(Long answerOptionId) {
		this.answerOptionId = answerOptionId;
	}
	public String getParticipantName() {
		return participantName;
	}
	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}
	
}
