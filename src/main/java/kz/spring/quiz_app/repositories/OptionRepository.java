package kz.spring.quiz_app.repositories;

import kz.spring.quiz_app.model.Options;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface OptionRepository extends JpaRepository<Options, Long> {
    List<Options> findByQuestions(Long id);
}
