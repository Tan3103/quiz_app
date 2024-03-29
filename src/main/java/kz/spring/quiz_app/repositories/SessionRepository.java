package kz.spring.quiz_app.repositories;

import kz.spring.quiz_app.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface SessionRepository extends JpaRepository<Session, Integer> {
    List<Session> findByUserId(int id);
}
