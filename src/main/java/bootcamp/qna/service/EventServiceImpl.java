package bootcamp.qna.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import bootcamp.qna.domain.Event;
import bootcamp.qna.domain.Quiz;
import bootcamp.qna.dto.EventDto;
import bootcamp.qna.dto.EventWithQuestionsDto;
import bootcamp.qna.exception.GeneralHttpException;
import bootcamp.qna.mapper.EventMapper;
import bootcamp.qna.repository.EventRepository;
import bootcamp.qna.repository.QuizRepository;

@Service
public class EventServiceImpl implements EventService {
	private EventRepository eventRepository;
	private EventMapper eventMapper;
	private QuizRepository quizRepository;
	
	public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper, QuizRepository quizRepo) {
		this.eventRepository = eventRepository;
		this.eventMapper = eventMapper;
		this.quizRepository = quizRepo;
	}

	@Override
	public List<EventDto> getAllEvents() {
		List<Event> events = (List<Event>) eventRepository.findAll();
		List<EventDto> eventDtos = events.stream().map(ev -> eventMapper.eventToDto(ev)).collect(Collectors.toList());
		return eventDtos;
	}

	@Override
	public EventDto getEventById(long id) {
		Optional<Event> event = eventRepository.findById(id);
		if(!event.isPresent()) {
			throw new GeneralHttpException("Event not found for id value = " + id, HttpStatus.NOT_FOUND);
		}
		EventDto eventDto = eventMapper.eventToDto(event.get());
		return eventDto;
	}

	@Override
	public void save(EventDto eventDto) {
		Optional<Quiz> quiz = quizRepository.findById(eventDto.getQuizId());
		if(!quiz.isPresent()) {
			throw new GeneralHttpException("Quiz not found for id value = " + eventDto.getQuizId(), HttpStatus.NOT_FOUND);
		}
		Event event = eventMapper.dtoToEvent(eventDto);
		event.setQuiz(quiz.get());
		eventRepository.save(event);
	}

	@Override
	public void delete(long id) {
		eventRepository.deleteById(id);
	}

	@Override
	public Optional<EventDto> getEventByAccessAndSecretCodes(String accessCode, String secretCode) {
		Optional<Event> oEvent = eventRepository.findByAccessCodeAndSecretCode(accessCode, secretCode);
		Optional<EventDto> oEventDto = Optional.empty();
		if(oEvent.isPresent()) {
			oEventDto = Optional.of(eventMapper.eventToDto(oEvent.get()));
		}
		return oEventDto;
	}

	@Override
	public EventWithQuestionsDto getEventWithQuestions(String accessCode) {
		Optional<Event> oEvent = eventRepository.findByAccessCode(accessCode);
		if(!oEvent.isPresent()) {
			throw new GeneralHttpException("Event not found for access code " + accessCode, HttpStatus.NOT_FOUND);
		}
		EventWithQuestionsDto eventWithQuestionsDto = eventMapper.eventToDtoWithQuestions(oEvent.get());
		return eventWithQuestionsDto;
	}

	@Override
	public Optional<EventDto> getEventByAccessCode(String accessCode) {
		Optional<Event> oEvent = eventRepository.findByAccessCode(accessCode);
		Optional<EventDto> oEventDto = Optional.empty();
		if(oEvent.isPresent()) {
			oEventDto = Optional.of(eventMapper.eventToDto(oEvent.get()));
		}
		return oEventDto;
	}
}
