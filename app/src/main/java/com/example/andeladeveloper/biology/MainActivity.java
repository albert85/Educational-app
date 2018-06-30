package com.example.andeladeveloper.biology;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText getQuestionOneSuppliedAnswer, getQuestionFiveSuppliedAns;
    private CheckBox getQuestionTwoOptionOne, getGetQuestionTwoOptionTwo, getGetQuestionTwoOptionThree, getGetQuestionTwoOptionFour;
    private CheckBox getQuestionSixOptionOne, getGetQuestionSixOptionTwo, getGetQuestionSixOptionThree, getGetQuestionSixOptionFour;
    private int correctAnsSelected = 0, wrongAnsSelected = 0;
    private RadioButton getQuestionThreeAns, getQuestionFourAns, getQuestionSevenAns;
    private Button getSubmitBtn, getScoreBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the answer supplied by the user
        getQuestionOneSuppliedAnswer = findViewById(R.id.answer_one);

        //get all question two options
        getQuestionTwoOptionOne = findViewById(R.id.question_two_option_one);
        getGetQuestionTwoOptionTwo = findViewById(R.id.question_two_option_two);
        getGetQuestionTwoOptionThree = findViewById(R.id.question_two_option_three);
        getGetQuestionTwoOptionFour = findViewById(R.id.question_two_option_four);

        // get question three selected answer
        getQuestionThreeAns = findViewById(R.id.question_three_option_two_radio);

        //get question four selected answer
        getQuestionFourAns = findViewById(R.id.question_four_option_one);

        //get the answer supplied by the user for question five
        getQuestionFiveSuppliedAns = findViewById(R.id.question_five_answer_edit);

        //get all question Six options
        getQuestionSixOptionOne = findViewById(R.id.question_six_option_one_chk);
        getGetQuestionSixOptionTwo = findViewById(R.id.question_six_option_two_chk);
        getGetQuestionSixOptionThree = findViewById(R.id.question_six_option_three_chk);
        getGetQuestionSixOptionFour = findViewById(R.id.question_six_option_four_chk);

        //get question seven selected answer
        getQuestionSevenAns = findViewById(R.id.question_seven_option_one_radio);

        // find submit and score button
        getSubmitBtn = findViewById(R.id.submit_btn);
        getScoreBtn = findViewById(R.id.score_btn);

        // dont allow access to check score button until the quiz is submitted
        getScoreBtn.setClickable(false);
        getScoreBtn.setAlpha(0.3f);

    }

    /*
    * @descriptions This method check question one correct answer is supplied
    *
    * */

    public void checkIfQuestionOneAnswerIsCorrect(){

        if(getQuestionOneSuppliedAnswer.getText().toString().trim().equalsIgnoreCase("mosquito")){
            correctAnsSelected++;
        }else{
            wrongAnsSelected++;
        }
    }

    /*
     * @descriptions This method check question two correct answers are selected
     *
     * */

    public void checkIfQuestionTwoAnswerAreSelected(){

        boolean IsOptionOneSelected = getQuestionTwoOptionOne.isChecked();
        boolean IsOptionTwoSelected = getGetQuestionTwoOptionTwo.isChecked();
        boolean IsOptionThreeSelected = getGetQuestionTwoOptionThree.isChecked();
        boolean IsOptionFourSelected = getGetQuestionTwoOptionFour.isChecked();

        if(!IsOptionFourSelected && IsOptionOneSelected && IsOptionTwoSelected && IsOptionThreeSelected){
            correctAnsSelected++;
        }else{
            wrongAnsSelected++;
        }


    }


    /*
     * @descriptions This method check question three correct answer is selected
     *
     * */

    public void checkIfQuestionThreeAnswerAreSelected(){

        boolean checkIfCorrectedAnsSelected = getQuestionThreeAns.isChecked();

        if(checkIfCorrectedAnsSelected){
            correctAnsSelected++;
        }else {
            wrongAnsSelected++;
        }

    }


    /*
     * @descriptions This method check question four correct answer is selected
     *
     * */

    public void checkIfQuestionFourAnswerAreSelected(){

        boolean checkIfCorrectedAnsSelected = getQuestionFourAns.isChecked();

        if(checkIfCorrectedAnsSelected){
            correctAnsSelected++;
        }else {
            wrongAnsSelected++;
        }

    }


    /*
    *
    * @descriptions This method check if question five answer is supplied
    *
    * */

    public void checkIfQuestionFiveAnswerAreSelected(){
        if(getQuestionFiveSuppliedAns.getText().toString().trim().equalsIgnoreCase("human immunodeficiency virus")){
            correctAnsSelected++;
        }else{
            wrongAnsSelected++;
        }

    }


    /*
     *
     * @descriptions This method check if question six answers are supplied
     *
     * */

    public void checkIfQuestionSixAnswerAreSelected(){
        boolean IsOptionOneSelected = getQuestionSixOptionOne.isChecked();
        boolean IsOptionTwoSelected = getGetQuestionSixOptionTwo.isChecked();
        boolean IsOptionThreeSelected = getGetQuestionSixOptionThree.isChecked();
        boolean IsOptionFourSelected = getGetQuestionSixOptionFour.isChecked();

        if(!IsOptionFourSelected && IsOptionOneSelected && IsOptionTwoSelected && !IsOptionThreeSelected){
            correctAnsSelected++;
        }else{
            wrongAnsSelected++;
        }
    }

    /*
     * @descriptions This method check question four correct answer is selected
     *
     * */

    public void checkIfQuestionSevenAnswerAreSelected(){

        boolean checkIfCorrectedAnsSelected = getQuestionSevenAns.isChecked();

        if(checkIfCorrectedAnsSelected){
            correctAnsSelected++;
        }else {
            wrongAnsSelected++;
        }
    }


    /*
     * @descriptions This method check number of correct answer selected
     *
     * */

    public void checkAnswerSelected(View view) {
        correctAnsSelected =0;
        wrongAnsSelected = 0;

        checkIfQuestionOneAnswerIsCorrect();
        checkIfQuestionTwoAnswerAreSelected();
        checkIfQuestionThreeAnswerAreSelected();
        checkIfQuestionFourAnswerAreSelected();
        checkIfQuestionFiveAnswerAreSelected();
        checkIfQuestionSixAnswerAreSelected();
        checkIfQuestionSevenAnswerAreSelected();

        Toast.makeText(this, String.format(getString(R.string.message), correctAnsSelected, wrongAnsSelected), Toast.LENGTH_LONG).show();

        // enable score button
        getScoreBtn.setClickable(true);;
        getScoreBtn.setAlpha(1f);

        //disable submit button
        getSubmitBtn.setClickable(false);
        getSubmitBtn.setAlpha(0.3f);

    }


    /*
     * @descriptions This method nagivate the page to a score sheet
     *
     * */

    public void go_to_next_question(View view) {
        Intent intent = new Intent(MainActivity.this, summaryActivity.class);
        intent.putExtra("correct_selected_answer", correctAnsSelected);
        intent.putExtra("total_question", (correctAnsSelected + wrongAnsSelected));
        startActivity(intent);
    }
}
