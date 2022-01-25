package bootcamp.qna.api.v1;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bootcamp.qna.dto.AnswerGroupDto;
import bootcamp.qna.dto.EventWithQuestionsDto;
import bootcamp.qna.dto.PostQuestionDto;
import bootcamp.qna.service.EventService;
import bootcamp.qna.service.QuestionEventService;

@RestController
public class PresenterRestController {
	private EventService eventService;
	private QuestionEventService questionEventService;

	public PresenterRestController(EventService eventService, QuestionEventService questionEventService) {
		this.eventService = eventService;
		this.questionEventService = questionEventService;
	}
	
	@RequestMapping(path="/api/v1/presenter/questions", method=RequestMethod.GET)
	public EventWithQuestionsDto getQuestions(String accessCode) {
		EventWithQuestionsDto eventWithQuestionsDto = eventService.getEventWithQuestions(accessCode);
		return eventWithQuestionsDto;
	}
	
	@RequestMapping(path="/api/v1/presenter/question", method=RequestMethod.GET)
	public boolean questionEventStatus(String accessCode, Long questionId) {
		return questionEventService.isQuestionEventProcessed(accessCode, questionId);
	}
	
	@RequestMapping(path="/api/v1/presenter/question", method=RequestMethod.POST)
	public Long postQuestion(PostQuestionDto postQuestionDto) {
		return questionEventService.postQuestion(postQuestionDto.getAccessCode(), postQuestionDto.getQuestionId());
	}
	
	@RequestMapping(path="/api/v1/presenter/question/{id}", method=RequestMethod.PUT)
	public void stopQuestion(@PathVariable(value="id", required=true) Long questionEventId) {
		questionEventService.stopQuestion(questionEventId);
	}
	
	@RequestMapping(path="/api/v1/presenter/questionEventAnswers", method=RequestMethod.GET)
	public List<AnswerGroupDto> getQuestionEventAnswers(
			@RequestParam(value="accessCode", required=true) String accessCode,
			@RequestParam(value="questionId", required=true) Long questionId){
		List<AnswerGroupDto> answerGroupDtos = questionEventService.getAnswerGroup(accessCode, questionId);
		return answerGroupDtos;
	}
	
	@RequestMapping(path="/api/v1/presenter/participantNames", method=RequestMethod.GET)
	public List<String>  getParticipantNames(
			@RequestParam(value="accessCode", required=true) String accessCode,
			@RequestParam(value="questionId", required=true) Long questionId,
			@RequestParam(value="answerOptionId", required=true) Long answerOptionId) {
		List<String> names = questionEventService.getParticipantNames(accessCode, questionId, answerOptionId);
		return names;
	}
}
