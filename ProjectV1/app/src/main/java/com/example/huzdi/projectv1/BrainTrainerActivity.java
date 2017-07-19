package com.example.huzdi.projectv1;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class BrainTrainerActivity extends AppCompatActivity {

    ArrayList<Integer> answers = new ArrayList<>();

    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    TextView timerTextView;
    TextView pointsTextView;
    TextView sumTextView;
    TextView resultTextView;

    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button startButton;
    Button playAgainButton;

    RelativeLayout gameRelativeLayout;

    public void start(View view ){

        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brain_trainer);

        startButton = (Button) findViewById(R.id.buttonStart);
        sumTextView = (TextView) findViewById(R.id.sum_textView);
        resultTextView = (TextView) findViewById(R.id.result_textVieew) ;
        pointsTextView = (TextView) findViewById(R.id.points_textView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        gameRelativeLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);

        button0 = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button2);
        button2 = (Button) findViewById(R.id.button3);
        button3 = (Button) findViewById(R.id.button4);

        playAgain(findViewById(R.id.playAgainButton));




    }
    public void chooseAnswer(View view){

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            Log.i("correct","correct");
            score++;
            resultTextView.setText("Correct!");
        } else {
            resultTextView.setText("Wrong!");
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/"+Integer.toString(numberOfQuestions));
        generateQuestion();
    }


    public void generateQuestion(){

        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " +  Integer.toString(b));


        locationOfCorrectAnswer = random.nextInt(4);

        answers.clear();
        int incorrectAnswer;

        for(int i = 0; i < 4;i++){
            if(i== locationOfCorrectAnswer){
                answers.add(a+b);

            } else {
                incorrectAnswer = random.nextInt(41);
                while (incorrectAnswer == a + b){
                    incorrectAnswer = random.nextInt(41);
                }
                answers.add(incorrectAnswer);

            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }


    public void playAgain(View view) {
        score = 0;
        numberOfQuestions = 0;

        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        generateQuestion();


        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long l) {

                timerTextView.setText(String.valueOf(l/1000) + "s");

            }

            @Override
            public void onFinish() {

                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your score: " + Integer.toString(score) + "/"+Integer.toString(numberOfQuestions));

            }
        }.start();
    }
    }
