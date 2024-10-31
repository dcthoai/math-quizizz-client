package math.client.dto.response;

@SuppressWarnings("unused")
public class Question {

    private String question, answer, expression;

    public Question() {}

    public Question(String question, String answer, String expression) {
        this.answer = answer;
        this.question = question;
        this.expression = expression;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getExpression() {
        return expression;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
