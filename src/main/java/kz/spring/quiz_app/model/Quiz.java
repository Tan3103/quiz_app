package kz.spring.quiz_app.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "quiz")
public class Quiz {

    @Id
    @Column(name = "quiz_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "subject")
    private String subject;

    @OneToMany(mappedBy = "quiz")
    private List<Questions> questionsList;

    @OneToMany(mappedBy = "quiz")
    private List<Session> sessions;
}
