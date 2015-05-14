package kar34.washington.edu.quizdroid;

import java.util.List;

public interface TopicRepository {
    public List<Topic> getTopics();

    public void addTopic(Topic topic);

    public int getNumTopics();

    public Topic getTopics(String s);

    public void addTopics(List<Topic> tl);
}
