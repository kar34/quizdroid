package kar34.washington.edu.quizdroid;

import android.app.IntentService;
import android.content.Intent;


public class Service extends IntentService {
    public Service() {
        super("Service");
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        String url = workIntent.getStringExtra("requestUrl");
    }
}
