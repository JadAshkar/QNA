package bootcamp.qna.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import bootcamp.qna.domain.Event;
import bootcamp.qna.dto.EventDto;
import bootcamp.qna.dto.EventWithQuestionsDto;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {
	@Mappings({
		@Mapping(target="quizName", source="event.quiz.name"),
		@Mapping(target="quizId", source="event.quiz.id")
	})
	public EventDto eventToDto(Event event);
	public Event dtoToEvent(EventDto eventDto);
	@Mappings({
		@Mapping(target="quizName", source="event.quiz.name"),
		@Mapping(target="quizId", source="event.quiz.id"),
		@Mapping(target="questions", source="event.quiz.questions")
	})
	public EventWithQuestionsDto eventToDtoWithQuestions(Event event);
}
