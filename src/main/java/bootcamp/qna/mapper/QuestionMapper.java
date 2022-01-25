package bootcamp.qna.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import bootcamp.qna.domain.Question;
import bootcamp.qna.dto.QuestionDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionMapper {
	@Mapping(target="quizId", source="question.quiz.id")
	public QuestionDto questionToDto(Question question);
	public Question DtoToQuestion(QuestionDto questionDto);
}
