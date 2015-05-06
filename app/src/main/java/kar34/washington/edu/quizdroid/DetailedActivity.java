package kar34.washington.edu.quizdroid;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Set;



public class DetailedActivity extends ActionBarActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        this.intent = getIntent();
        Quiz quiz = Quiz.getInstance();
        quiz.start(intent.getStringExtra("subject"));

        if (savedInstanceState == null) {
            Fragment overviewFrag = new OverviewFrag();
            getFragmentManager().beginTransaction().add(R.id.quiz, overviewFrag).commit();
        }
    }


    // OVERVIEW FRAGMENT
    public static class OverviewFrag extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
            final View view = inflater.inflate(R.layout.activity_overview, container, false);
            Quiz quiz = Quiz.getInstance();

            TextView subject = (TextView) view.findViewById(R.id.subject);
            TextView numQs = (TextView) view.findViewById(R.id.numqs);
            TextView desc = (TextView) view.findViewById(R.id.desc);
            subject.setText(quiz.getSubject());
            numQs.setText(quiz.getNumQsAsString() + " questions in this quiz");
            desc.setText(quiz.getDescription());

            Button begin = (Button) view.findViewById(R.id.begin);
            begin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment qFrag = new QuestionFrag();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.quiz, qFrag);
                    ft.commit();
                }
            });

            return view;
        }
    }

    // QUESTION FRAGMENT
    public static class QuestionFrag extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
            // stores data
            final View view = inflater.inflate(R.layout.activity_question, container, false);
            final Quiz quiz = Quiz.getInstance();
            String question = quiz.getQuestion();
            final HashMap<Boolean, String> options = quiz.getAnswerOptions();
            final Set<String> answers = quiz.getOptionValues().keySet();

            // places remaining elements
            final Button button = (Button) view.findViewById(R.id.submit);
            button.setVisibility(View.INVISIBLE);

            final RadioGroup radios = (RadioGroup) view.findViewById(R.id.radios);
            TextView questionText = (TextView) view.findViewById(R.id.question);
            questionText.setText(question);
            RadioButton radio1 = (RadioButton) view.findViewById(R.id.radio1);
            RadioButton radio2 = (RadioButton) view.findViewById(R.id.radio2);
            RadioButton radio3 = (RadioButton) view.findViewById(R.id.radio3);
            RadioButton radio4 = (RadioButton) view.findViewById(R.id.radio4);

            int i = 0;
            for (String answer : answers) {
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
                    RadioButton chosen = (RadioButton) view.findViewById(radios.getCheckedRadioButtonId());
                    String correct = quiz.getAnswer(options);

                    Fragment aFrag = new AnswerFrag();
                    Bundle b = new Bundle();
                    b.putString("chosen", chosen.getText().toString());
                    b.putString("correct", correct);
                    aFrag.setArguments(b);

                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.quiz, aFrag);
                    ft.commit();

                }
            });

            // radio listener
            radios.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    button.setVisibility(View.VISIBLE);
                }
            });

            return view;
        }
    }

    // ANSWER FRAGMENT
    public static class AnswerFrag extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
            final View view = inflater.inflate(R.layout.activity_answer, container, false);
            final Quiz quiz = Quiz.getInstance();
            Bundle b = getArguments();

            quiz.incCurrentQ();
            if(b.getString("correct").equals(b.getString("chosen"))) {
                quiz.incNumCorrect();
            }

            // ui elements
            TextView chosenText = (TextView) view.findViewById(R.id.chosen);
            TextView correctText = (TextView) view.findViewById(R.id.correct);
            TextView statsText = (TextView) view.findViewById(R.id.stats);

            chosenText.setText("Your answer: " + b.getString("chosen"));
            correctText.setText("Correct answer: " + b.getString("correct"));
            statsText.setText("You have " + quiz.getNumCorrect() + " of " + quiz.getNumQs() + " correct");

            final Button button = (Button) view.findViewById(R.id.button);
            if (quiz.isDone())
                button.setText("Finish");
            else
                button.setText("Next");

            // button listener
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(quiz.isDone()) {
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Fragment qFrag = new QuestionFrag();
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.quiz, qFrag);
                        ft.commit();
                    }
                }
            });

            return view;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detailed, menu);
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
