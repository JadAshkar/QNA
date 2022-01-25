package bootcamp.qna.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import bootcamp.qna.dto.EventDto;
import bootcamp.qna.dto.EventWithQuestionsDto;

@Service
public interface EventService {
	public List<EventDto> getAllEvents();
	public EventDto getEventById(long id);
	public void save(EventDto eventDto);
	public void delete(long id);
	public Optional<EventDto> getEventByAccessAndSecretCodes(String accessCode, String secretCode);
	public EventWithQuestionsDto getEventWithQuestions(String accessCode);
	public Optional<EventDto> getEventByAccessCode(String accessCode);
}
