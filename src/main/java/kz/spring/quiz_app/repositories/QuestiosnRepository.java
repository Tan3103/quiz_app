package kz.spring.quiz_app.repositories;


import kz.spring.quiz_app.model.Questions;
import kz.spring.quiz_app.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface QuestiosnRepository extends JpaRepository<Questions, Long> {
    List<Questions> findByQuiz(Quiz quiz);
}
