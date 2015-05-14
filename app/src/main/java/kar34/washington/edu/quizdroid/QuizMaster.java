package kar34.washington.edu.quizdroid;

import java.util.HashMap;

public class QuizMaster {
    private static QuizMaster instance = new QuizMaster();
    private Topic topic;
    private String subject;
    private String description;
    private int numQs;
    private int currentQ;
    private int numCorrect;

    private QuizMaster() {   }

    public static QuizMaster getInstance() {
        return instance;
    }

    public void start(Topic topic) {
        this.topic = topic;
        numQs = topic.getQuestionList().size();
        currentQ = 1;
        numCorrect = 0;
    }

    public boolean isDone() {
        return this.currentQ > this.numQs;
    }

    public boolean isLastQ() {
        return this.currentQ == this.numQs;
    }

    public void incCurrentQ() {
        this.currentQ++;
    }

    public int getNumCorrect() {
        return this.numCorrect;
    }

    public int getNumQs() {
        return this.numQs;
    }

    public String getNumQsAsString() {
        return Integer.toString(this.numQs);
    }

    public String getDescription() {
        return this.description;
    }

    public void incNumCorrect() {
        this.numCorrect++;
    }

    public String getSubject() {
        return this.subject;
    }

    public Question getQuestion() {
        if(this.isDone()) {
            return null;
        } else {
            return topic.getQuestionList().get(currentQ - 1);
        }
    }


}
