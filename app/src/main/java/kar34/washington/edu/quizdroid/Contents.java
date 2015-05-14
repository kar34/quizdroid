package kar34.washington.edu.quizdroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Contents {
    public static final String[] topicText = {"Math", "Physics", "Marvel Super Heroes"};
    public static final String[] mathQs = {"What is 2 + 5?", "What is 3 * 3?", "What is 4 - 5?", "What is 1 + 1?", "What is 69 * 1?"};
    public static final String[] physicsQs = {"Who came up with the theory of relativity?", "Newton created which law(s)?", "True or false: Physics courses are offered at UW.", "Who is responsible for the law of universal graduation?"};
    public static final String[] marvelQs = {"What color is The Hulk?", "True or false: The Iron Man is a Marvel Super Hero.", "True or false: Ronald McDonald is a Marvel Super Hero."};

    public static final Map<String, String[]> topicDescrMap = new HashMap<String, String[]>() {{
        put(Contents.topicText[0], new String[]{"Math is a wonderful thing, math is a really cool thing",
                "You can add things, subtract things, divide, subtract, derive, measure, estimate, calculate and etc"});
        put(Contents.topicText[1], new String[]{"How things move, bro",
                "Its the natural science that involves the study of matter and its motion through space and time, along with related concepts such as energy and force (Wikipedia)."});
        put(Contents.topicText[2], new String[]{"Its like the hulk and stuff",
                "Marvel made lots of superheroes and villains that are characters in comics, movies, video games and much more."});
    }};

    public static List<Topic> getTopics() {
        List<Topic> topics = new ArrayList<Topic>();
        for (String name : topicDescrMap.keySet()) {
            Topic topic = new Topic(name, topicDescrMap.get(name)[0], topicDescrMap.get(name)[1], getQuestions(name));
            topics.add(topic);
        }
        return topics;
    }

    public static HashMap<String, TreeMap<String, HashMap<String, Boolean>>> masterMap = new HashMap<String, TreeMap<String, HashMap<String, Boolean>>>() {{

        put(Contents.topicText[0], new TreeMap<String, HashMap<String, Boolean>>() {{
            put(mathQs[0], new HashMap<String, Boolean>() {{
                put("3", false);
                put("6", false);
                put("7", true);
                put("12", false);
            }});
            put(mathQs[1], new HashMap<String, Boolean>() {{
                put("9", true);
                put("12", false);
                put("6", false);
                put("8", false);
            }});
            put(mathQs[2], new HashMap<String, Boolean>() {{
                put("0", false);
                put("4", false);
                put("9", false);
                put("-1", true);
            }});
            put(mathQs[3], new HashMap<String, Boolean>() {{
                put("0", false);
                put("3", false);
                put("2", true);
                put("1", false);
            }});
            put(mathQs[4], new HashMap<String, Boolean>() {{
                put("69", true);
                put("0", false);
                put("1", false);
                put("699", false);
            }});
        }});
        put(Contents.topicText[1], new TreeMap<String, HashMap<String, Boolean>>() {{
            put(physicsQs[0], new HashMap<String, Boolean>() {{
                put("It was never proved", false);
                put("Newton", false);
                put("Albert Einstein", true);
                put("All of the above", false);
            }});
            put(physicsQs[1], new HashMap<String, Boolean>() {{
                put("Newton's First Law", false);
                put("Newton's Second Law", false);
                put("Newton's Third Law", false);
                put("All of the above", true);
            }});
            put(physicsQs[2], new HashMap<String, Boolean>() {{
                put("True", true);
                put("False", false);
                put("Idk", false);
                put("Idc", false);
            }});
            put(physicsQs[3], new HashMap<String, Boolean>() {{
                put("Some guy", false);
                put("Newton", true);
                put("Who cares really...", false);
                put("Can I phone a friend?", false);
            }});
        }});
        put(Contents.topicText[2], new TreeMap<String, HashMap<String, Boolean>>() {{
            put(marvelQs[0], new HashMap<String, Boolean>() {{
                put("White", false);
                put("Orange", false);
                put("Red", false);
                put("Green", true);
            }});
            put(marvelQs[1], new HashMap<String, Boolean>() {{
                put("True", true);
                put("False", false);
                put("Idk", false);
                put("Idc", false);
            }});
            put(marvelQs[2], new HashMap<String, Boolean>() {{
                put("True", false);
                put("False", true);
                put("Idk", false);
                put("Idc", false);
            }});
        }});
    }};

    private static List<Question> getQuestions(String name) {
        List<Question> questions = new ArrayList<Question>();
        TreeMap<String, HashMap<String, Boolean>> qaMap = masterMap.get(name);
        for (String question : qaMap.keySet()) {
            HashMap<String, Boolean> optionMap = qaMap.get(question);
            List<String> options = new ArrayList<String>();
            int check = -1;
            int i = 0;
            for (String answer : optionMap.keySet()) {
                options.add(answer);
                if (check == -1 && optionMap.get(answer))
                    check = i;
                else
                    i++;
            }
            Question q = new Question(question, options, check);
            questions.add(q);
        }

        return questions;
    }
}
