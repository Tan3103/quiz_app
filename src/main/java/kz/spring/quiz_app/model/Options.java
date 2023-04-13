package kz.spring.quiz_app.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "options")
public class Options {

    @Id
    @Column(name = "option_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionId;

    @Column(name = "optionText")
    private String optionText;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "questions_id")
    private Questions questions;
}
