package htwberlin.Karteikarten.api;

public class Flashcard {

    private long id;
    private String answer;
    private String questions;
    private String category;

    public Flashcard(long id, String answer, String questions, String category) {
        this.id = id;
        this.answer = answer;
        this.questions = questions;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
