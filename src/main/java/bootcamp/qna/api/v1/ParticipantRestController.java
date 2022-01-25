package bootcamp.qna.api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bootcamp.qna.dto.AnswerDto;
import bootcamp.qna.dto.QuestionEventDto;
import bootcamp.qna.service.QuestionEventService;

@RestController
public class ParticipantRestController {
	
	private QuestionEventService questionEventService;

	public ParticipantRestController(QuestionEventService questionEventService) {
		this.questionEventService = questionEventService;
	}
	
	@RequestMapping(path="/api/v1/participant/nextQuestion", method=RequestMethod.GET)
	public QuestionEventDto nextQuestion(
			@RequestParam(value="accessCode", required=true) String accessCode,
			@RequestParam(value="participantName", required=true) String participantName) {
		QuestionEventDto res = questionEventService.getNextQuestion(accessCode, participantName);
		return res;
	}
	
	@RequestMapping(path="/api/v1/participant/answer", method=RequestMethod.POST)
	public void answerQuestion(AnswerDto answerDto) {
		questionEventService.answerQuestion(answerDto);
	}
}
