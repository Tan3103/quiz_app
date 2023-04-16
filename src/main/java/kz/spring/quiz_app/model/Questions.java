package kz.spring.quiz_app.model;


import jakarta.annotation.security.DenyAll;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "questions")
public class Questions {
    @Id
    @Column(name = "questions_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    public Questions(String questionTitle, int chose, int optionCorrect, Quiz quiz) {
        this.questionTitle = questionTitle;
        this.chose = chose;
        this.optionCorrect = optionCorrect;
        this.quiz = quiz;
    }

    @Column(name = "question_title")
    private String questionTitle;

    @OneToMany(mappedBy = "questions")
    private List<Options> options;

    @Column(name="chose")
    private int chose;

    @Column(name = "option_correct")
    private int optionCorrect;

    @ManyToOne()
    @JoinColumn(name = "quiz_id", referencedColumnName = "quiz_id" )
    private Quiz quiz;



}
