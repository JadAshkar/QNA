package bootcamp.qna.api.v1;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bootcamp.qna.dto.QuestionDto;
import bootcamp.qna.exception.GeneralHttpException;
import bootcamp.qna.service.QuizService;

@RestController
public class AdminRestController {
	private QuizService quizService;

	public AdminRestController(QuizService quizService) {
		this.quizService = quizService;
	}
	
	@RequestMapping(path="/api/v1/admin/quizes/quiz/question", method=RequestMethod.POST)
	public void addQuestion(@Valid @RequestBody QuestionDto questionDto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			throw new GeneralHttpException("Validation Errors", HttpStatus.BAD_REQUEST);
		}
		
		quizService.addQuestion(questionDto);
	}
}
