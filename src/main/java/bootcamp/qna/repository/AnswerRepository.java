package bootcamp.qna.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bootcamp.qna.domain.Answer;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long>{

}
