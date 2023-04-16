package kz.spring.quiz_app.services;

import kz.spring.quiz_app.model.Questions;
import kz.spring.quiz_app.model.Quiz;
import kz.spring.quiz_app.model.Session;
import kz.spring.quiz_app.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void saveSession(Session session){
        sessionRepository.save(session);
    }

    public int getScore(Quiz quiz) {
        int correct = 0;

        if (quiz.getQuestionsList() != null) {
            for (Questions q : quiz.getQuestionsList()) {
                if ((q.getChose()+1) == q.getOptionCorrect()) {
                    correct++;
                }
            }
        }
        return correct;
    }

    public List<Session> findByUserId(int id){
        return sessionRepository.findByUserId(id);
    }
    public List<Session> getSessions(){
        return sessionRepository.findAll();
    }
}
