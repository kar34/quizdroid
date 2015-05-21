package kar34.washington.edu.quizdroid;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.*;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.view.View;
import android.net.ConnectivityManager;


public class MainActivity extends ActionBarActivity {

    private String prefKey = "edu.washington.kar34.PREFERENCE_FILE_KEY";
    String intervalKey = "request_interval";
    String defaultInterval = "5";
    public ConnectivityManager cm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QuizApp quiz = QuizApp.getInstance();
        ListView lv = (ListView) findViewById(R.id.theList);
        final ArrayAdapter<String> adapterItems = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Contents.topicText);
        lv.setAdapter(adapterItems);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent next = new Intent(MainActivity.this, DetailedActivity.class);
                String subject = adapterItems.getItem(position);
                next.putExtra("subject", subject);
                startActivity(next);
            }
        });

        cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = cm.getActiveNetworkInfo();

        boolean isConnected = network != null && network.isConnectedOrConnecting();
        boolean isAirplane = false;
        try {
            isAirplane = Settings.Global.getInt(this.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON) != 0;
        } catch (Exception e) {}

        if (!isConnected) {
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Looks like there's no connection, yo");

            if (isAirplane) {
                b.setMessage("Airplane mode is currently on. Would you like to turn it off?")
                    .setPositiveButton("Yay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS));
                        }
                    })
                    .setNegativeButton("Nay", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) { }
                    });
                b.create().show();
            } else
                Toast.makeText(this.getApplicationContext(), "Sorry man, you don't have any bars.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences sp = this.getSharedPreferences(prefKey, Context.MODE_PRIVATE);
        int milisecs = Integer.parseInt(sp.getString(intervalKey, defaultInterval)) * 1000;

        Intent next = new Intent(this, QuestionReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, next, 0);

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        am.setInexactRepeating(AlarmManager.RTC, System.currentTimeMillis(), milisecs, pi);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, PreferenceActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
