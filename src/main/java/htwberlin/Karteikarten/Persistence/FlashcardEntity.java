package htwberlin.Karteikarten.Persistence;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity( name = "Flashcards")
public class FlashcardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column (name ="Question", nullable = false)
    private String question;
    @Column (name = "Answer", nullable = false)
    private String answer;

    @Column (name = "Category")
    private String category;

    // Konstruktor, Getter und Setter

    public FlashcardEntity() {
    }

    public FlashcardEntity(String question, String answer, String category) {
        this.question = question;
        this.answer = answer;
        this.category = category;
    }

    // Getter und Setter für id, question und answer

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<FlashcardEntity> getFlashcards(){
        return null;
        //TODO: Alle Flashcards ausgeben
    }
}
