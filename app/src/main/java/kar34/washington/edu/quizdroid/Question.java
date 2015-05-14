package kar34.washington.edu.quizdroid;

import java.util.List;

public class Question {
    private String question;
    private List<String> options;
    private int corrAnsPos;

    public Question(String question, List<String> answers, int corrAnsPos) {
        this.question = question;
        this.options = answers;
        this.corrAnsPos = corrAnsPos;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getCorrAnsPos() {
        return corrAnsPos;
    }

    public void setCorrAnsPos(int corrAnsPos) {
        this.corrAnsPos = corrAnsPos;
    }

}
