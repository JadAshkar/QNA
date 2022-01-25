package bootcamp.qna.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import bootcamp.qna.domain.AnswerGroup;
import bootcamp.qna.domain.QuestionEvent;
import bootcamp.qna.dto.AnswerGroupDto;
import bootcamp.qna.dto.QuestionEventDto;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionEventMapper {
	public AnswerGroupDto answerGroupToDto(AnswerGroup answerGroup);
	@Mappings({
		@Mapping(target="text", source="questionEvent.question.text"),
		@Mapping(target="questionEventId", source="questionEvent.id"),
		@Mapping(target="answerOptions", source="questionEvent.question.answerOptions")
	})
	public QuestionEventDto questionEventToDto(QuestionEvent questionEvent);
}
