package com.timkontrakan.appberhitung;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startGame, button0, button1, button2, button3, playAgainButton;
    TextView questionSum, results, points, timerTxt;
    RelativeLayout gamePlayRelative;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score =0;
    int numberOfQuestion = 0;

    public void playAgain(View view){

        score = 0;
        numberOfQuestion = 0;
        timerTxt.setText("30s");
        points.setText("0/0");
        results.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long l) {
                timerTxt.setText(String.valueOf(l / 1000) + "s");
                button0.setVisibility(View.VISIBLE);
                button1.setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);
                button3.setVisibility(View.VISIBLE);

            }

            @Override
            public void onFinish() {
                results.setText("Score Anda : " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));
                timerTxt.setText("0s");
                playAgainButton.setVisibility(View.VISIBLE);
                button0.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
            }
        }.start();

    }

    public void generateQuestion(){
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        questionSum.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = random.nextInt(4);
        answers.clear();

        int incorrectAnswer;
        for (int i=0; i<4; i++){

            if (i == locationOfCorrectAnswer){
                answers.add(a + b);
            }
            else {
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

    public void start(View view){

        startGame.setVisibility(View.INVISIBLE);
        gamePlayRelative.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }

    public void chooseAnswer(View view){

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){

            score++;
            results.setText("Benar :)");

        }else {
            results.setText("Salah !");
        }
        numberOfQuestion++;
        points.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));
        generateQuestion();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGame = findViewById(R.id.startGame);
        questionSum = findViewById(R.id.questionSum);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        results = findViewById(R.id.results);
        points = findViewById(R.id.points);
        timerTxt = findViewById(R.id.timerTxt);
        gamePlayRelative = findViewById(R.id.gamePlayRelative);
        playAgainButton = findViewById(R.id.playAgainButton);


    }
}
