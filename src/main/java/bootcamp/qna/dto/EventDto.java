package bootcamp.qna.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EventDto {
	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	private String accessCode;
	@NotBlank
	@Size(min=4)
	private String secretCode;
	private boolean ended;
	private String quizName;
	private long quizId;
	
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
	public String getQuizName() {
		return quizName;
	}
	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}
	public long getQuizId() {
		return quizId;
	}
	public void setQuizId(long quizId) {
		this.quizId = quizId;
	}
}
