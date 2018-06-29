package com.example.andeladeveloper.biology;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class introActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
    }

    public void startQuiz(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
