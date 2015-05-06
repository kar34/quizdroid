package kar34.washington.edu.quizdroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.view.View;
import android.content.Intent;

public class MainActivity extends ActionBarActivity {

    public String[] subjects = {"Math", "Physics", "Marvel Super Heroes"};
    private ListView theList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        theList = (ListView) findViewById(R.id.theList);
        final ArrayAdapter<String> adapterItems = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, subjects);
        theList.setAdapter(adapterItems);

        theList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
