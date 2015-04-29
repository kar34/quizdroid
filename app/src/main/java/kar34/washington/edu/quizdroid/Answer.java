package kar34.washington.edu.quizdroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class Answer extends ActionBarActivity {

    private Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        // intent
        Intent intent = getIntent();
        quiz = (Quiz) intent.getSerializableExtra("quiz");
        String chosen = intent.getStringExtra("chosen");
        String correct = intent.getStringExtra("correct");

        quiz.incCurrentQ();
        if(chosen.equals(correct)) {
            quiz.incNumCorrect();
        }


        // ui elements
        TextView chosenText = (TextView) findViewById(R.id.chosen);
        TextView correctText = (TextView) findViewById(R.id.correct);
        TextView statsText = (TextView) findViewById(R.id.stats);

        chosenText.setText("Your answer: " + chosen);
        correctText.setText("Correct answer: " + correct);
        statsText.setText("You have " + quiz.getNumCorrect() + " of " + quiz.getNumQs() + " correct");

        final Button button = (Button) findViewById(R.id.button);
        if(quiz.isDone())
            button.setText("Finish");
        else
            button.setText("Next");


        // button listener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quiz.isDone()) {
                    Intent main = new Intent(Answer.this, MainActivity.class);
                    startActivity(main);
                } else {
                    Intent nextQ = new Intent(Answer.this, Question.class);
                    nextQ.putExtra("quiz", quiz);
                    startActivity(nextQ);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_answer, menu);
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
