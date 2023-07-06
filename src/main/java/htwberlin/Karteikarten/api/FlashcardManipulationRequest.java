package htwberlin.Karteikarten.api;

public class FlashcardManipulationRequest {

    private String answer;
    private String question;
    private String category;

    public FlashcardManipulationRequest(String answer, String question, String category) {
        this.answer = answer;
        this.question = question;
        this.category = category;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion(){
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
