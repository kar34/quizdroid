package kar34.washington.edu.quizdroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

public class QuestionReceiver extends BroadcastReceiver {

    String preference_file_key = "edu.washington.kar34.PREFERENCE_FILE_KEY";
    String default_request_url = "http://tednewardsandbox.site44.com/questions.json";
    String request_url_key = "request_url";

    public QuestionReceiver() {   }

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sp = context.getSharedPreferences(preference_file_key, Context.MODE_PRIVATE);
        Toast.makeText(context, sp.getString(request_url_key, default_request_url), Toast.LENGTH_SHORT).show();
    }
}
