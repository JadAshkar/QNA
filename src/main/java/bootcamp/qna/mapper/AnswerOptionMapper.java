package bootcamp.qna.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import bootcamp.qna.domain.AnswerOption;
import bootcamp.qna.dto.AnswerOptionDto;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnswerOptionMapper {
	public AnswerOptionDto answerOptionToDto(AnswerOption answerOption);
	public AnswerOption DtoToAnswerOption(AnswerOptionDto answerOptionDto);
}
