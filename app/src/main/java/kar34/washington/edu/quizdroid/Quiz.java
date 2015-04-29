package kar34.washington.edu.quizdroid;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.io.Serializable;
import java.util.HashMap;

public class Quiz implements Serializable {

    private String subject;
    private String description;
    private int numQs;
    private int currentQ;
    private int numCorrect;
    private String[] questions;
    private HashMap<String, HashMap<Boolean, String>> qMap;
    private HashMap<String, HashMap<String, Boolean>> aMap;


    public Quiz(String subject) {
        this.subject = subject;
        this.currentQ = 1;
        this.numCorrect = 0;
        Log.d("this is a subject", this.subject);

        if (subject.equals("Math")) {
            this.description = "math is a wonderful thing, math is a really cool thing";
            this.numQs = 5;
            this.questions = new String[] {"What is 2 + 5?", "What is 3 * 3?", "What is 4 - 5?", "What is 1 + 1?", "What is 69 * 1?"};
        } else if (subject.equals("Physics")) {
            this.description = "how things move, bro";
            this.numQs = 4;
            this.questions = new String[] {"Who came up with the theory of relativity?", "Newton created which law(s)?", "True or false: Physics courses are offered at UW.", "Who is responsible for the law of universal graduation?"};
        } else if (subject.equals("Marvel Super Heroes")) {
            this.description = "its like the hulk and stuff";
            this.numQs = 3;
            this.questions = new String[] {"What color is The Hulk?", "True or false: The Iron Man is a Marvel Super Hero.", "True or false: Ronald McDonald is a Marvel Super Hero."};
        } else {
            throw new IllegalArgumentException();
        }
        fillQMap();
        fillAMap();
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

    public String getQuestion() {
        if (isDone())
            return null;
        return this.questions[this.currentQ - 1];
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

    public String getAnswer(HashMap<Boolean, String> options) {
        return options.get(true);
    }

    public HashMap<Boolean, String> getAnswerOptions() {
        if (getQuestion() == null)
            return null;
        else
            return this.qMap.get(getQuestion());
    }

    public HashMap<String, Boolean> getOptionValues() {
        if (getQuestion() == null)
            return null;
        else
            return this.aMap.get(getQuestion());
    }

    public int getNumCorrect() {
        return this.numCorrect;
    }

    public int getNumQs() {
        return this.numQs;
    }


    public void fillQMap() {
        if (this.subject.equals("Math")) {
            this.qMap = new HashMap<String, HashMap<Boolean, String>>() {{
                put(questions[0], new HashMap<Boolean, String>() {{
                    put(false, "3");
                    put(false, "6");
                    put(true, "7");
                    put(false, "12");
                }});
                put(questions[1], new HashMap<Boolean, String>() {{
                    put(true, "9");
                    put(false, "12");
                    put(false, "6");
                    put(false, "8");
                }});
                put(questions[2], new HashMap<Boolean, String>() {{
                    put(false, "0");
                    put(false, "4");
                    put(false, "9");
                    put(true, "-1");
                }});
                put(questions[3], new HashMap<Boolean, String>() {{
                    put(false, "0");
                    put(false, "3");
                    put(true, "2");
                    put(false, "1");
                }});
                put(questions[4], new HashMap<Boolean, String>() {{
                    put(true, "69");
                    put(false, "0");
                    put(false, "1");
                    put(false, "699");
                }});
            }};
        } else if (this.subject.equals("Physics")) {
            this.qMap = new HashMap<String, HashMap<Boolean, String>>() {{
                put(questions[0], new HashMap<Boolean, String>() {{
                    put(false, "It was never proved");
                    put(false, "Newton");
                    put(true, "Albert Einstein");
                    put(false, "All of the above");
                }});
                put(questions[1], new HashMap<Boolean, String>() {{
                    put(false, "Newton's First Law");
                    put(false, "Newton's Second Law");
                    put(false, "Newton's Third Law");
                    put(true, "All of the above");
                }});
                put(questions[2], new HashMap<Boolean, String>() {{
                    put(true, "True");
                    put(false, "False");
                    put(false, "Idk");
                    put(false, "Idc");
                }});
                put(questions[3], new HashMap<Boolean, String>() {{
                    put(false, "Some guy");
                    put(true, "Newton");
                    put(false, "Who cares really...");
                    put(false, "Can I phone a friend?");
                }});
            }};
        } else if (this.subject.equals("Marvel Super Heroes")) {
            this.qMap = new HashMap<String, HashMap<Boolean, String>>() {{
                put(questions[0], new HashMap<Boolean, String>() {{
                    put(false, "White");
                    put(false, "Orange");
                    put(false, "Red");
                    put(true, "Green");
                }});
                put(questions[1], new HashMap<Boolean, String>() {{
                    put(true, "True");
                    put(false, "False");
                    put(false, "Idk");
                    put(false, "Idc");
                }});
                put(questions[2], new HashMap<Boolean, String>() {{
                    put(false, "True");
                    put(true, "False");
                    put(false, "Idk");
                    put(false, "Idc");
                }});
            }};
        }
    }

    public void fillAMap() {
        if (this.subject.equals("Math")) {
            this.aMap = new HashMap<String, HashMap<String, Boolean>>() {{
                put(questions[0], new HashMap<String, Boolean>() {{
                    put("3", false);
                    put("6", false);
                    put("7", true);
                    put("12", false);
                }});
                put(questions[1], new HashMap<String, Boolean>() {{
                    put("9", true);
                    put("12", false);
                    put("6", false);
                    put("8", false);
                }});
                put(questions[2], new HashMap<String, Boolean>() {{
                    put("0", false);
                    put("4", false);
                    put("9", false);
                    put("-1", true);
                }});
                put(questions[3], new HashMap<String, Boolean>() {{
                    put("0", false);
                    put("3", false);
                    put("2", true);
                    put("1", false);
                }});
                put(questions[4], new HashMap<String, Boolean>() {{
                    put("69", true);
                    put("0", false);
                    put("1", false);
                    put("699", false);
                }});
            }};
        } else if (this.subject.equals("Physics")) {
            this.aMap = new HashMap<String, HashMap<String, Boolean>>() {{
                put(questions[0], new HashMap<String, Boolean>() {{
                    put("It was never proved", false);
                    put("Newton", false);
                    put("Albert Einstein", true);
                    put("All of the above", false);
                }});
                put(questions[1], new HashMap<String, Boolean>() {{
                    put("Newton's First Law", false);
                    put("Newton's Second Law", false);
                    put("Newton's Third Law", false);
                    put("All of the above", true);
                }});
                put(questions[2], new HashMap<String, Boolean>() {{
                    put("True", true);
                    put("False", false);
                    put("Idk", false);
                    put("Idc", false);
                }});
                put(questions[3], new HashMap<String, Boolean>() {{
                    put("Some guy", false);
                    put("Newton", true);
                    put("Who cares really...", false);
                    put("Can I phone a friend?", false);
                }});
            }};
        } else if (this.subject.equals("Marvel Super Heroes")) {
            this.aMap = new HashMap<String, HashMap<String, Boolean>>() {{
                put(questions[0], new HashMap<String, Boolean>() {{
                    put("White", false);
                    put("Orange", false);
                    put("Red", false);
                    put("Green", true);
                }});
                put(questions[1], new HashMap<String, Boolean>() {{
                    put("True", true);
                    put("False", false);
                    put("Idk", false);
                    put("Idc", false);
                }});
                put(questions[2], new HashMap<String, Boolean>() {{
                    put("True", false);
                    put("False", true);
                    put("Idk", false);
                    put("Idc", false);
                }});
            }};
        }
    }


}
