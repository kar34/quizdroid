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

    String preference_file_key = "edu.washington.kar34.PREFERENCE_FILE_KEY";
    String default_request_url = "http://tednewardsandbox.site44.com/questions.json";
    String default_request_interval = "5";
    String request_url_key = "request_url";
    String request_interval_key = "request_interval";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        SharedPreferences sp = getSharedPreferences(preference_file_key, Context.MODE_PRIVATE);
        String requestUrl = sp.getString(request_url_key, default_request_url);
        String requestInterval = sp.getString(request_interval_key, default_request_interval);

        final EditText urlText = (EditText) findViewById(R.id.request_url);
        urlText.setText(requestUrl);
        final EditText intervalText = (EditText) findViewById(R.id.request_interval);
        intervalText.setText(requestInterval);

        /* Button urlButton = (Button) findViewById(R.id.request_url_btn);
        final Button intervalButton = (Button) findViewById(R.id.request_interval_btn);

        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button tapped = (Button) v;
                if (tapped.getId() == R.id.request_interval_btn)
                    intervalText.setText(default_request_interval);
                else if (tapped.getId() == R.id.request_url_btn)
                    urlText.setText(default_request_url);
            }
        };

        urlButton.setOnClickListener(buttonListener);
        intervalButton.setOnClickListener(buttonListener); */
    }

    @Override
    protected void onPause() {
        super.onPause();

        EditText urlText = (EditText) findViewById(R.id.request_url);
        EditText intervalText = (EditText) findViewById(R.id.request_interval);
        SharedPreferences sp = this.getSharedPreferences(preference_file_key, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(request_url_key, urlText.getText().toString());
        edit.putString(request_interval_key, intervalText.getText().toString());
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