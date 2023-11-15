package com.example.csc221test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.csc221test.databinding.MainActivityLayoutBinding;

public class MainActivity extends AppCompatActivity {
    private QuestionAnswers questionAnswers = new QuestionAnswers();
    private TextView questionView, scoreNum;
    private Button buttonOne, buttonTwo, buttonThree, buttonFour;
    private int score = 0;
    private String answer;
    private int questionNumber = 0; // First question = 0

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        questionView = (TextView)findViewById(R.id.question);
        scoreNum = (TextView)findViewById(R.id.scoreNum);
        buttonOne = (Button)findViewById(R.id.choiceOne);
        buttonTwo = (Button)findViewById(R.id.choiceTwo);
        buttonThree = (Button)findViewById(R.id.choiceThree);
        buttonFour = (Button)findViewById(R.id.choiceFour);
        updateQuestion();

        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // On click button logic
                if (buttonOne.getText() == answer){ // What happens when we get the right answer
                    score += 1;
                    updateScore(score);
                    updateQuestion();
                }
            }
        });

        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // On click button logic
                if (buttonTwo.getText() == answer){ // What happens when we get the right answer
                    score += 1;
                    updateScore(score);
                    updateQuestion();
                }
            }
        });

        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // On click button logic
                if (buttonThree.getText() == answer){ // What happens when we get the right answer
                    score += 1;
                    updateScore(score);
                    updateQuestion();
                }
            }
        });

        buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // On click button logic
                if (buttonFour.getText() == answer){ // What happens when we get the right answer
                    score += 1;
                    updateScore(score);
                    updateQuestion();
                }
            }
        });

    }
    private void updateQuestion(){
        questionView.setText(QuestionAnswers.getQuestion(questionNumber));
        buttonOne.setText(QuestionAnswers.getAnswerOne(questionNumber));
        buttonTwo.setText(QuestionAnswers.getAnswerTwo(questionNumber));
        buttonThree.setText(QuestionAnswers.getAnswerThree(questionNumber));
        buttonFour.setText(QuestionAnswers.getAnswerFour(questionNumber));
        answer = QuestionAnswers.getSolution(questionNumber);
        questionNumber++;
    }
    private void updateScore(int newScore) {
        scoreNum.setText(""+(newScore));
    }
}
