package math.client.model;

import java.util.List;
import java.util.UUID;

public class Question {

    private final String questionID;
    private final List<String> options;

    public Question(List<String> options) {
        this.questionID = UUID.randomUUID().toString();
        this.options = options;
    }

    public String getQuestionID() {
        return questionID;
    }

    public List<String> getOptions() {
        return options;
    }
}
