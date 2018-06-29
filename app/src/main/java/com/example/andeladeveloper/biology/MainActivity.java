package com.example.andeladeveloper.biology;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Resources res;
    int question_index = 0, answer_index = 0, insight_index = 0, question_length = 0;
    int corrected_score = 0;
    TextView getquestion_txt, getinsight_txt, get_answer_status;
    RadioButton get_yes_rad, get_no_rad;
    RadioGroup answer_rad;
    Button getCheckBtn, getNextBtn;
    String get_answer_chosen="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find the question textview
        getquestion_txt = findViewById(R.id.question_txt);

        // find the insight textview
        getinsight_txt = findViewById(R.id.insert_insight_txt);

        //find selected answer
        get_yes_rad = findViewById(R.id.yes_chk);
        get_no_rad = findViewById(R.id.no_chk);

        //find check and next button
        getCheckBtn = findViewById(R.id.check_btn);
        getNextBtn = findViewById(R.id.next_btn);

        //get selected answer
        answer_rad = findViewById(R.id.selected_answer_rad);

        // get answer status
        get_answer_status = findViewById(R.id.answer_status);

        //disable next button until answer is chosen
        getNextBtn.setClickable(false);
        getNextBtn.setAlpha(.3f);


        //set resource to get questions
        res = getResources();

        //load the first question
        loadQuestion();

        //get selected answerr
        getSelectedAnswer();
    }

    public void getSelectedAnswer(){

        answer_rad.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.yes_chk:
                        get_answer_chosen = "Yes";
                        //Toast.makeText(MainActivity.this, "Yes", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.no_chk:
                        //Toast.makeText(MainActivity.this, "No", Toast.LENGTH_SHORT).show();
                        get_answer_chosen = "No";
                        break;
                        default:
                            break;

                }
            }
        });

    }

    /*
     * @description This method load questions from the array
     * */

    public void loadQuestion(){
        //get the first question
        String question = getQuestion(question_index);



        //Display the question
        DisplayAnswer(question);
    }

    /*
     * @description This method display questions on the application
     * */

    public void DisplayAnswer(String question_to_display){
        getquestion_txt.setText(question_to_display);
    }

    public void DisplayInsight(String displayInsight){
        getinsight_txt.setText(displayInsight);
    }


    /*
    * @description This method get questions from the array
    * @param {String} question_index
    * @returns {String} question of the question_index in the array
    * */

    public String getQuestion(int question_index){
        String[] question = res.getStringArray(R.array.question);
        //get the total questions available
        question_length = question.length;
        return question[question_index];
    }


    /*
     * @description This method get answer from the array
     * @param {String} question_index
     * @returns {String} question of the answer_index in the array
     * */

    public String getAnswer(int answer_index){
        String[] answer = res.getStringArray(R.array.answer);

        return answer[answer_index];
    }

    /*
     * @description This method get insight from the array
     * @param {String} question_index
     * @returns {String} question of the insight_index in the array
     * */

    public String getInsight(int insight_index){
        String[] insight = res.getStringArray(R.array.insight);
        return insight[insight_index];
    }

    public void checkAnswerSelected(View view) {

        if(getquestion_txt.length() != 0){

            //get the right answer
            String answer = getAnswer(question_index);

            //get insight
            String insight = getInsight(question_index);

            //get the user selected answer and compare
            if(answer.equals(get_answer_chosen)){
                get_answer_status.setText("Correct");
                get_answer_status.setTextColor(Color.parseColor("#18a404"));
                corrected_score++;
            }else{
                get_answer_status.setText("Wrong");
                get_answer_status.setTextColor(Color.RED);
            }



            getCheckBtn.setClickable(false);
            getCheckBtn.setAlpha(.5f);

            getNextBtn.setClickable(true);
            getNextBtn.setAlpha(1f);

            getinsight_txt.setText(insight);
            getinsight_txt.setMovementMethod(new ScrollingMovementMethod());


        }else{

        Toast.makeText(MainActivity.this, "Please Choose An answer", Toast.LENGTH_SHORT).show();
        }


    }


    public void go_to_next_question(View view) {
        // clear previous data
        answer_rad.clearCheck();
        getinsight_txt.setText("");

        getNextBtn.setClickable(false);
        getNextBtn.setAlpha(.3f);

        getCheckBtn.setClickable(true);
        getCheckBtn.setAlpha(1f);

        get_answer_status.setText("None");
        get_answer_status.setTextColor(Color.parseColor("#000000"));


        // Toast.makeText(MainActivity.this, "question_index: "+ question_index + "question_length "+ (question_length-1), Toast.LENGTH_SHORT).show();

        //Load next question
        if(question_index == (question_length-1)){
            //go to summary page
            Intent intent = new Intent(MainActivity.this, summaryActivity.class);
            intent.putExtra("corrected_score", corrected_score);
            intent.putExtra("total_question", (question_length));
            startActivity(intent);
        }else{

        question_index++;
        loadQuestion();
        }
    }
}
