package bootcamp.qna.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bootcamp.qna.domain.AnswerOption;

@Repository
public interface AnswerOptionRepository extends CrudRepository<AnswerOption, Long> {

}
