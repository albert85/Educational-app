package com.example.andeladeveloper.biology;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class summaryActivity extends AppCompatActivity {

    double corrected_score = 0, total_score = 0;
    TextView getscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        //get
        Intent intent = getIntent();

        // get score textview
        getscore = findViewById(R.id.score_txt);

        //get corrected answer
         corrected_score = intent.getIntExtra("corrected_score",0);
         total_score = intent.getIntExtra("total_question",0);

        calculatescore();


    }

    public void calculatescore(){
        double percentage = Math.ceil(corrected_score/total_score * 100);

        //display the score
        getscore.setText(String.format("%s%%", String.valueOf(percentage)));

    }

    public void startQuiz(View view) {
        Intent intent = new Intent(summaryActivity.this, introActivity.class);
        startActivity(intent);
    }
}
