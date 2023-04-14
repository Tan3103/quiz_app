package kz.spring.quiz_app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "session")
public class Session {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "quiz_id", referencedColumnName = "quiz_id")
    private Quiz quiz;

    @Column(name = "score")
    private int score;

    @Column(name = "start_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startAt;


    @Column(name = "end_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endAt;

    public Session(User user, Quiz quiz, int score, Date startAt, Date endAt) {
        this.user = user;
        this.quiz = quiz;
        this.score = score;
        this.startAt = startAt;
        this.endAt = endAt;
    }
}
