package bootcamp.qna.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bootcamp.qna.domain.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {

}
