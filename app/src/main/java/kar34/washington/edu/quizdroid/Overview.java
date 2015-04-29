package kar34.washington.edu.quizdroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.util.Log;



public class Overview extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        final Intent intent = getIntent();
        final Quiz quiz = new Quiz(intent.getStringExtra("subject"));

        TextView subject = (TextView) findViewById(R.id.subject);
        TextView numQs = (TextView) findViewById(R.id.numqs);
        TextView desc = (TextView) findViewById(R.id.desc);
        subject.setText(intent.getStringExtra("subject"));
        numQs.setText(quiz.getNumQsAsString() + " questions in this quiz");
        desc.setText(quiz.getDescription());

        Button begin = (Button) findViewById(R.id.begin);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent question = new Intent(Overview.this, Question.class);
                question.putExtra("quiz", quiz);
                startActivity(question);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_overview, menu);
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
