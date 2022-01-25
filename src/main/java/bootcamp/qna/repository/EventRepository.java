package bootcamp.qna.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bootcamp.qna.domain.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
	public Optional<Event> findByAccessCodeAndSecretCode(String accessCode, String secretCode);
	public Optional<Event> findByAccessCode(String accessCode);
}
