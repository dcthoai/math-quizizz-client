package math.client.dto.response;

@SuppressWarnings("unused")
public class Question {

    private String numbers, target;

    public Question() {}

    public Question(String numbers, String target) {
        this.numbers = numbers;
        this.target = target;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
