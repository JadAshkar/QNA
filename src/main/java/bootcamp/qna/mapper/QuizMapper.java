package bootcamp.qna.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import bootcamp.qna.domain.Quiz;
import bootcamp.qna.dto.QuizDto;
import bootcamp.qna.dto.QuizWithQuestionsDto;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuizMapper {
	public QuizDto quizToDto(Quiz quiz);
	public Quiz dtoToQuiz(QuizDto quizDto);
	public QuizWithQuestionsDto quizToQuizWithOptionsDto(Quiz quiz);
}
