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
import java.util.List;



public class DetailedActivity extends ActionBarActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        this.intent = getIntent();
        QuizApp quiz = QuizApp.getInstance();
        TopicRepository repo = quiz.getRepository();
        Topic topic = repo.getTopics(this.intent.getStringExtra("subject"));
        QuizMaster master = QuizMaster.getInstance();
        master.start(topic);


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
            QuizMaster quiz = QuizMaster.getInstance();

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
            final QuizMaster master = QuizMaster.getInstance();
            final Question q = master.getQuestion();
            final List<String> options = q.getOptions();

            // places remaining elements
            final Button button = (Button) view.findViewById(R.id.submit);
            button.setVisibility(View.INVISIBLE);

            final RadioGroup radios = (RadioGroup) view.findViewById(R.id.radios);
            TextView questionText = (TextView) view.findViewById(R.id.question);
            questionText.setText(q.getQuestion());
            RadioButton radio1 = (RadioButton) view.findViewById(R.id.radio1);
            RadioButton radio2 = (RadioButton) view.findViewById(R.id.radio2);
            RadioButton radio3 = (RadioButton) view.findViewById(R.id.radio3);
            RadioButton radio4 = (RadioButton) view.findViewById(R.id.radio4);

            int i = 0;
            for (String option : options) {
                if (i == 0)
                    radio1.setText(option);
                else if (i == 1)
                    radio2.setText(option);
                else if (i == 2)
                    radio3.setText(option);
                else
                    radio4.setText(option);
                i++;
            }

            if (master.isLastQ())
                button.setText("Finish");


            // button listener
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RadioButton chosen = (RadioButton) view.findViewById(radios.getCheckedRadioButtonId());
                    String correct = options.get(q.getCorrAnsPos());

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
            final QuizMaster master = QuizMaster.getInstance();
            Bundle b = getArguments();

            master.incCurrentQ();
            if(b.getString("correct").equals(b.getString("chosen"))) {
                master.incNumCorrect();
            }

            // ui elements
            TextView chosenText = (TextView) view.findViewById(R.id.chosen);
            TextView correctText = (TextView) view.findViewById(R.id.correct);
            TextView statsText = (TextView) view.findViewById(R.id.stats);

            chosenText.setText("Your answer: " + b.getString("chosen"));
            correctText.setText("Correct answer: " + b.getString("correct"));
            statsText.setText("You have " + master.getNumCorrect() + " of " + master.getNumQs() + " correct");

            final Button button = (Button) view.findViewById(R.id.button);
            if (master.isDone())
                button.setText("Finish");
            else
                button.setText("Next");

            // button listener
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(master.isDone()) {
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
