package bootcamp.qna.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import bootcamp.qna.domain.Answer;
import bootcamp.qna.domain.AnswerGroup;
import bootcamp.qna.domain.AnswerOption;
import bootcamp.qna.domain.Event;
import bootcamp.qna.domain.Participant;
import bootcamp.qna.domain.Question;
import bootcamp.qna.domain.QuestionEvent;
import bootcamp.qna.dto.AnswerDto;
import bootcamp.qna.dto.AnswerGroupDto;
import bootcamp.qna.dto.QuestionEventDto;
import bootcamp.qna.exception.GeneralHttpException;
import bootcamp.qna.mapper.QuestionEventMapper;
import bootcamp.qna.repository.AnswerOptionRepository;
import bootcamp.qna.repository.AnswerRepository;
import bootcamp.qna.repository.EventRepository;
import bootcamp.qna.repository.QuestionEventRepository;
import bootcamp.qna.repository.QuestionRepository;

@Service
public class QuestionEventServiceImpl implements QuestionEventService{

	private EventRepository eventRepository;
	private QuestionRepository questionRepository;
	private QuestionEventRepository questionEventRepository;
	private QuestionEventMapper questionEventMapper;
	private AnswerOptionRepository answerOptionRepository;
	private AnswerRepository answerRepository;
	
	public QuestionEventServiceImpl(EventRepository eventRepository, QuestionRepository questionRepository,
			QuestionEventRepository questionEventRepository, QuestionEventMapper questionEventMapper,
			AnswerOptionRepository answerOptionRepo, AnswerRepository answerRepo) {
		this.eventRepository = eventRepository;
		this.questionRepository = questionRepository;
		this.questionEventRepository = questionEventRepository;
		this.questionEventMapper = questionEventMapper;
		this.answerOptionRepository = answerOptionRepo;
		this.answerRepository = answerRepo;
	}

	@Override
	public Long postQuestion(String accessCode, Long questionId) {
		//I need the event
		//I need the question
		//create new questionevent and save it. 
		Optional<Event> oEvent = eventRepository.findByAccessCode(accessCode);
		if(!oEvent.isPresent()) {
			throw new GeneralHttpException("Event not found for access code " + accessCode, HttpStatus.NOT_FOUND);
		}
		Optional<Question> oQuestion = questionRepository.findById(questionId);
		if(!oQuestion.isPresent()) {
			throw new GeneralHttpException("Questoin not found for id " + questionId, HttpStatus.NOT_FOUND);
		}
		QuestionEvent qe = new QuestionEvent();
		qe.setQuestion(oQuestion.get());
		qe.setEvent(oEvent.get());
		qe.setProcessed(false);
		qe = questionEventRepository.save(qe);
		return qe.getId();
	}

	@Override
	public void stopQuestion(Long questionEventId) {
		Optional<QuestionEvent> oQuestionEvent = questionEventRepository.findById(questionEventId);
		if(!oQuestionEvent.isPresent()) {
			throw new GeneralHttpException("Question Event not found for id " + questionEventId, HttpStatus.NOT_FOUND);
		}
		QuestionEvent qe = oQuestionEvent.get();
		qe.setProcessed(true);
		questionEventRepository.save(qe);
	}

	@Override
	public boolean isQuestionEventProcessed(String accessCode, Long questionId) {
		Optional<QuestionEvent> oQuestionEvent = questionEventRepository.getQuestionEvent(accessCode, questionId);
		if(!oQuestionEvent.isPresent()) {
			throw new GeneralHttpException("Question Event not found for accessCode  " + accessCode, HttpStatus.NOT_FOUND);
		}
		return oQuestionEvent.get().isProcessed();
	}

	@Override
	public List<AnswerGroupDto> getAnswerGroup(String accessCode, Long questionId) {
		List<AnswerGroup> answerGroups = questionEventRepository.getQuestionEventAnswerGroups(accessCode, questionId);
		List<AnswerGroupDto> answerGroupDtos = answerGroups.stream().map(a -> questionEventMapper.answerGroupToDto(a))
				.collect(Collectors.toList());
		return answerGroupDtos;
	}

	@Override
	public List<String> getParticipantNames(String accessCode, Long questionId, Long answerOptionId) {
		List<Participant> participantNames = questionEventRepository.getParticipantNames(accessCode, questionId, answerOptionId);
		List<String> names = participantNames.stream().map(p -> p.getPersonName()).collect(Collectors.toList());
		return names;
	}

	@Override
	public QuestionEventDto getNextQuestion(String accessCode, String participantName) {
		Optional<QuestionEvent> oQuestionEvent = questionEventRepository.getNextQuestion(accessCode, participantName);
		if(!oQuestionEvent.isPresent()) {
			throw new GeneralHttpException("Question Event not found", HttpStatus.NOT_FOUND);
		}
		QuestionEventDto qeDto = questionEventMapper.questionEventToDto(oQuestionEvent.get());
		return qeDto;
	}

	@Override
	public void answerQuestion(AnswerDto answerDto) {
		Optional<QuestionEvent> oQuestionEvent = questionEventRepository.findById(answerDto.getQuestionEventId());
		if(!oQuestionEvent.isPresent()) {
			throw new GeneralHttpException("Question Event not found", HttpStatus.NOT_FOUND);
		}
		Optional<AnswerOption> oAnswerOption = answerOptionRepository.findById(answerDto.getAnswerOptionId());
		if(!oAnswerOption.isPresent()) {
			throw new GeneralHttpException("Answer Option not found", HttpStatus.NOT_FOUND);
		}
		Answer answer = new Answer();
		answer.setPersonName(answerDto.getParticipantName());
		answer.setQuestionEvent(oQuestionEvent.get());
		answer.setAnswerOption(oAnswerOption.get());
		answerRepository.save(answer);
	}

}
