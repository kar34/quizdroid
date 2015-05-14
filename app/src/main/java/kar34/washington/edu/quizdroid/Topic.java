package kar34.washington.edu.quizdroid;


import java.util.List;

public class Topic {

    private String name;
    private String sDescr;
    private String lDescr;
    private List<Question> questionList;


    public Topic(String name, String sDescr, String lDescr, List<Question> questionList) {
        this.name = name;
        this.sDescr = sDescr;
        this.lDescr = lDescr;
        this.questionList = questionList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSDescr() {
        return sDescr;
    }

    public void setSDescr(String s) {
        this.sDescr = s;
    }

    public String getLDescr() {
        return lDescr;
    }

    public void setLDescr(String s) {
        this.lDescr = s;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> qs) {
        this.questionList = qs;
    }
}
