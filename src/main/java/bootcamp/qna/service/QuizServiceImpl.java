package bootcamp.qna.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import bootcamp.qna.domain.Question;
import bootcamp.qna.domain.Quiz;
import bootcamp.qna.dto.QuestionDto;
import bootcamp.qna.dto.QuizDto;
import bootcamp.qna.dto.QuizWithQuestionsDto;
import bootcamp.qna.exception.GeneralHttpException;
import bootcamp.qna.mapper.QuestionMapper;
import bootcamp.qna.mapper.QuizMapper;
import bootcamp.qna.repository.QuestionRepository;
import bootcamp.qna.repository.QuizRepository;

@Service
public class QuizServiceImpl implements QuizService {

	private QuizRepository quizRepository;
	private QuizMapper quizMapper;
	private QuestionRepository questionRepository;
	private QuestionMapper questionMapper;
	
	public QuizServiceImpl(QuizRepository quizRepo, QuizMapper quizMapper, 
			QuestionRepository questionRepo, QuestionMapper questionMapper) {
		this.quizRepository = quizRepo;
		this.quizMapper = quizMapper;
		this.questionRepository = questionRepo;
		this.questionMapper = questionMapper;
	}
	
	@Override
	public List<QuizDto> getAllQuizes() {
		List<Quiz> quizes = (List<Quiz>) quizRepository.findAll();
		List<QuizDto> quizDtos = quizes.stream().map(q -> quizMapper.quizToDto(q)).collect(Collectors.toList());
		return quizDtos;
	}

	@Override
	public void save(QuizDto quizDto) {
		Quiz quiz = quizMapper.dtoToQuiz(quizDto);
		quizRepository.save(quiz);
	}

	@Override
	public void delete(long id) {
		quizRepository.deleteById(id);
	}

	@Override
	public QuizWithQuestionsDto getQuizById(long id) {
		Optional<Quiz> quiz = quizRepository.findById(id);
		if(!quiz.isPresent()) {
			//throw an http based exception with http status not found 404
			throw new GeneralHttpException("Quiz of id " + id + " is not found", HttpStatus.NOT_FOUND);
		}
		QuizWithQuestionsDto quizDto = quizMapper.quizToQuizWithOptionsDto(quiz.get());
		return quizDto;
	}

	@Override
	public void addQuestion(QuestionDto questionDto) {
		Optional<Quiz> quiz = quizRepository.findById(questionDto.getQuizId());
		if(!quiz.isPresent()) {
			throw new GeneralHttpException("Quiz of id " + questionDto.getId() + " is not found", HttpStatus.NOT_FOUND);
		}
		Question question = questionMapper.DtoToQuestion(questionDto);
		question.getAnswerOptions().forEach(ao -> ao.setQuestion(question));
		question.setQuiz(quiz.get());
		questionRepository.save(question);
	}

}
