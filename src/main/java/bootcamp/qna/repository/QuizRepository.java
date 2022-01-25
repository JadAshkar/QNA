package bootcamp.qna.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bootcamp.qna.domain.Quiz;

@Repository
public interface QuizRepository extends CrudRepository<Quiz, Long>{

}
