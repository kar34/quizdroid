package kar34.washington.edu.quizdroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class QuestionReceiver extends BroadcastReceiver {

    String prefKey = "edu.washington.kar34.PREFERENCE_FILE_KEY";
    String defaultLink = "http://tednewardsandbox.site44.com/questions.json";
    String urlKey = "request_url";

    public QuestionReceiver() {   }

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sp = context.getSharedPreferences(prefKey, Context.MODE_PRIVATE);
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo network = cm.getActiveNetworkInfo();
        boolean isConnected = network != null && network.isConnectedOrConnecting();

        if (isConnected) {
            Intent next = new Intent(context, Service.class);
            next.putExtra("url", sp.getString(urlKey, defaultLink));
            context.startService(next);
        }
    }

}
