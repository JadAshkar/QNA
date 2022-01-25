package bootcamp.qna.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bootcamp.qna.domain.AnswerGroup;
import bootcamp.qna.domain.Participant;
import bootcamp.qna.domain.QuestionEvent;

@Repository
public interface QuestionEventRepository extends CrudRepository<QuestionEvent, Long>{
	
	@Query("Select qe from QuestionEvent qe "
			+ " Where qe.question.id = :questionId "
			+ " and qe.event.accessCode = :accessCode")
	public Optional<QuestionEvent> getQuestionEvent(String accessCode, Long questionId);
	
	@Query("Select qe, ao.id as answerOptionId, ao.optionOrder as answerOptionOrder, "
			+ "count(ao.id) as totalAnswers "
			+ "from QuestionEvent qe "
			+ "join qe.answers a "
			+ "join a.answerOption ao "
			+ "where qe.question.id = :questionId "
			+ "and qe.event.accessCode = :accessCode "
			+ "group by ao.id "
			+ "order by ao.id asc")
	public List<AnswerGroup> getQuestionEventAnswerGroups(String accessCode, Long questionId);
	
	@Query("Select qe, a.personName as personName "
			+ "from QuestionEvent qe "
			+ "join fetch qe.answers a "
			+ "where qe.event.accessCode = :accessCode "
			+ "and qe.question.id = :questionId "
			+ "and a.answerOption.id = :answerOptionId "
			+ "order by a.personName asc")
	public List<Participant> getParticipantNames(String accessCode, Long questionId, Long answerOptionId);
	
	@Query("Select qe from QuestionEvent qe "
			+ "where qe.event.accessCode = :accessCode "
			+ "and qe.processed = 0 "
			+ "and not exists "
			+ "(from Answer a where a.questionEvent = qe "
			+ "and LOWER(a.personName) = LOWER(:name))")
	public Optional<QuestionEvent> getNextQuestion(String accessCode, String name);
}
