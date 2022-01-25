package bootcamp.qna.service;

import java.util.List;

import org.springframework.stereotype.Service;

import bootcamp.qna.dto.AnswerDto;
import bootcamp.qna.dto.AnswerGroupDto;
import bootcamp.qna.dto.QuestionEventDto;

@Service
public interface QuestionEventService {
	public Long postQuestion(String accessCode, Long questionId);
	public void stopQuestion(Long questionEventId);
	public boolean isQuestionEventProcessed(String accessCode, Long questionId);
	public List<AnswerGroupDto> getAnswerGroup(String accessCode, Long questionId);
	public List<String> getParticipantNames(String accessCode, Long questionId, Long answerOptionId);
	public QuestionEventDto getNextQuestion(String accessCode, String participantName);
	public void answerQuestion(AnswerDto answerDto);
}
