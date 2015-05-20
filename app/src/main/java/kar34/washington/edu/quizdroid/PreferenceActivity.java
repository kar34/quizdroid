package kar34.washington.edu.quizdroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class PreferenceActivity extends ActionBarActivity {

    String prefKey = "edu.washington.kar34.PREFERENCE_FILE_KEY";
    String defaultLink = "http://tednewardsandbox.site44.com/questions.json";
    String defaultInterval = "5";
    String urlKey = "request_url";
    String intervalKey = "request_interval";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        SharedPreferences sp = getSharedPreferences(prefKey, Context.MODE_PRIVATE);
        String requestUrl = sp.getString(urlKey, defaultLink);
        String requestInterval = sp.getString(intervalKey, defaultInterval);

        final EditText urlText = (EditText) findViewById(R.id.urlText);
        urlText.setText(requestUrl);
        final EditText intervalText = (EditText) findViewById(R.id.intervalText);
        intervalText.setText(requestInterval);

    }

    @Override
    protected void onPause() {
        super.onPause();

        EditText urlText = (EditText) findViewById(R.id.urlText);
        EditText intervalText = (EditText) findViewById(R.id.intervalText);
        SharedPreferences sp = this.getSharedPreferences(prefKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(urlKey, urlText.getText().toString());
        edit.putString(intervalKey, intervalText.getText().toString());
        edit.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_preference, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}