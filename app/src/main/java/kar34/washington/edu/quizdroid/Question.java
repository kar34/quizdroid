package kar34.washington.edu.quizdroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.*;
import android.view.View;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Question extends ActionBarActivity {

    private Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        // Intent
        Intent intent = getIntent();
        quiz = (Quiz) intent.getSerializableExtra("quiz");
        String question = quiz.getQuestion();
        final HashMap<Boolean, String> options = quiz.getAnswerOptions();
        final Set<String> answers = quiz.getOptionValues().keySet();

        // places final elements
        final Button button = (Button) findViewById(R.id.submit);
        button.setVisibility(View.INVISIBLE);
        final RadioGroup radios = (RadioGroup) findViewById(R.id.radios);

        // places remaining elements
        TextView questionText = (TextView) findViewById(R.id.question);
        questionText.setText(question);
        RadioButton radio1 = (RadioButton) findViewById(R.id.radio1);
        RadioButton radio2 = (RadioButton) findViewById(R.id.radio2);
        RadioButton radio3 = (RadioButton) findViewById(R.id.radio3);
        RadioButton radio4 = (RadioButton) findViewById(R.id.radio4);

        int i = 0;
        spfor (String answer : answers) {
            if (i == 0)
                radio1.setText(answer);
            else if (i == 1)
                radio2.setText(answer);
            else if (i == 2)
                radio3.setText(answer);
            else
                radio4.setText(answer);
            i++;
        }

        if (quiz.isLastQ())
            button.setText("Finish");

        // button listener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton chosen = (RadioButton) findViewById(radios.getCheckedRadioButtonId());
                String correct = quiz.getAnswer(options);
                Intent answer = new Intent(Question.this, Answer.class);
                answer.putExtra("quiz", quiz);
                answer.putExtra("chosen", chosen.getText());
                answer.putExtra("correct", correct);
                startActivity(answer);
            }
        });

        // radio listener
        radios.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                button.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_question, menu);
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
