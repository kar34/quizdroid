package kar34.washington.edu.quizdroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

public class QuestionReceiver extends BroadcastReceiver {

    String prefKey = "edu.washington.kar34.PREFERENCE_FILE_KEY";
    String defaultLink = "http://tednewardsandbox.site44.com/questions.json";
    String urlKey = "request_url";

    public QuestionReceiver() {   }

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sp = context.getSharedPreferences(prefKey, Context.MODE_PRIVATE);
        Toast.makeText(context, sp.getString(urlKey, defaultLink), Toast.LENGTH_SHORT).show();
    }
}
