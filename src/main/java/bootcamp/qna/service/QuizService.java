package bootcamp.qna.service;

import java.util.List;

import org.springframework.stereotype.Service;

import bootcamp.qna.dto.QuestionDto;
import bootcamp.qna.dto.QuizDto;
import bootcamp.qna.dto.QuizWithQuestionsDto;

@Service
public interface QuizService {
	// 1. A function to provide list of all Quizes
	public List<QuizDto> getAllQuizes();
	// 2. A function to save a Quiz
	public void save(QuizDto quizDto);
	// 3. A function to delete a Quiz
	public void delete(long id);
	// 4. A function to get a quiz details including questions and options using the quiz id. 
	public QuizWithQuestionsDto getQuizById(long id);
	// 5. A function to add a question to a quiz. i.e. we are saving a question related to a quiz. 
	public void addQuestion(QuestionDto questionDto);
}
