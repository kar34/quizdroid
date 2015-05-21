package kar34.washington.edu.quizdroid;

import android.app.Application;
import java.util.List;

public class QuizApp extends Application {

    private static QuizApp instance;
    private static TopicRepository repo;

    @Override
    public void onCreate() {
        super.onCreate();
        if (instance != null)
            throw new IllegalStateException("Current game already in session.");
        else {
            instance = this;
            List<Topic> theList = Contents.getTopics();
            repo = RepositoryList.getInstance();
            repo.addTopics(theList);
        }
    }

    public TopicRepository getRepository() {
        return this.repo;
    }

    public static QuizApp getInstance() {
        return instance;
    }

}