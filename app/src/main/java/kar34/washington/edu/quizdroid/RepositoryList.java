package kar34.washington.edu.quizdroid;


import java.util.ArrayList;
import java.util.List;

public class RepositoryList implements TopicRepository {
    private static List<Topic> topics = new ArrayList<Topic>();
    private static RepositoryList instance = new RepositoryList();

    private RepositoryList() {   }

    public static RepositoryList getInstance() {
        return instance;
    }

    public void addTopic(Topic topic) {
        topics.add(topic);
    }

    public void addTopics(List<Topic> tl) {
        topics.addAll(tl);
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public Topic getTopics(String s) {
        Topic topic = null;
        for(Topic t: getTopics()) {
            if(t.getName().equals(s))
                topic = t;
        }
        return topic;
    }

    public int getNumTopics() {
        return topics.size();
    }
}
