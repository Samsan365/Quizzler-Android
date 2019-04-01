package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Thread.sleep;

public class MainActivity extends Activity {


    // TODO: Declare member variables here:
    private static Button mButtonTrue;
    private static Button mButtonFalse;
    private static TextView mScoreTextView;
    private static ProgressBar mProgressBar;
    TextView mQuestionTextView;
    int mIndex;
    int mQuestion;
    int mScore=0;

    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R .string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };

    // TODO: Declare constants here
    final int PROGRESS_BAR_INCREMENT = (int) Math.ceil(100.00/mQuestionBank.length);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Check if the savedInstanceState is present, if yes then load the previous state

        if(savedInstanceState != null){
            mScore = savedInstanceState.getInt("ScoreKey");
            mIndex = savedInstanceState.getInt("IndexKey");

        }
        else{
            mScore = 0;
            mIndex = 0;

        }


        mButtonTrue = findViewById(R.id.true_button);
        mButtonFalse = findViewById(R.id.false_button);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mScoreTextView = (TextView) findViewById(R.id.score);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        //Get the Question from the array
        mQuestion = mQuestionBank[mIndex].getmQuestionID();

        //Set the question on the Text View
        mQuestionTextView.setText(mQuestion);

        //Set the score on the text view
        mScoreTextView.setText("Score" + mScore + "/" + mQuestionBank.length);


        mButtonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast myToast = Toast.makeText(getApplicationContext(),"True Pressed", Toast.LENGTH_SHORT);
                //myToast.show();
                checkAnswer(true);
                updateQuestion();

            }
        });

        mButtonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast myToastFalse=Toast.makeText(getApplicationContext(),"False Pressed",Toast.LENGTH_SHORT);
                //myToastFalse.show();
                checkAnswer(false);
                updateQuestion();

            }
        }


        );
    }

    private void updateQuestion(){
        mIndex = (mIndex+1) % mQuestionBank.length;

        if(mIndex == 0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
                    alert.setTitle("GameOver");
                    alert.setCancelable(false);
                    alert.setMessage("You Scored " + mScore + " Points");
                    alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
                    alert.show();
        }
        mQuestion = mQuestionBank[mIndex].getmQuestionID();
        mQuestionTextView.setText(mQuestion);
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        mScoreTextView.setText("Score" + mScore + "/" + mQuestionBank.length);
    }

    private void checkAnswer(boolean userSelection){
        boolean correctAns = mQuestionBank[mIndex].ismAnswer();


        if(userSelection == correctAns){

            mScore = mScore+1;
            Toast.makeText(getApplicationContext(),R.string.correct_toast,Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(getApplicationContext(),R.string.incorrect_toast,Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        outState.putInt("ScoreKey", mScore);
        outState.putInt("IndexKey", mIndex);

    }
}