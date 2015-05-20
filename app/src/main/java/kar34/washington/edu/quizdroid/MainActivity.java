package kar34.washington.edu.quizdroid;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.view.View;
import android.content.Intent;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    String preference_file_key = "edu.washington.kar34.PREFERENCE_FILE_KEY";
    String request_interval_key = "request_interval";
    String default_request_interval = "5";
    private PendingIntent pi;
    private AlarmManager am;


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
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences sp = this.getSharedPreferences(preference_file_key, Context.MODE_PRIVATE);
        int milisecs = Integer.parseInt(sp.getString(request_interval_key, default_request_interval)) * 1000;

        Intent nextIntent = new Intent(this, QuestionReceiver.class);
        pi = PendingIntent.getBroadcast(this, 0, nextIntent, 0);

        am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
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
