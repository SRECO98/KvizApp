package com.sreco.quizmobilnoracunarstvo.make_your_quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sreco.quizmobilnoracunarstvo.MainActivity;
import com.sreco.quizmobilnoracunarstvo.R;
import com.sreco.quizmobilnoracunarstvo.file_system_base.NameOfKviz;

public class AddToSQLite extends AppCompatActivity {

    Dialog dialog;
    TextView nameOfQuizTextView, currentQuestion;
    EditText nameOfQuizFromEditText, numberOfQuestionsFromEditText;
    EditText etAnswer1, etAnswer2, etAnswer3, etAnswer4, etQuestion;
    AppCompatButton buttonOkFromDialog, buttonCancelFromDialog, buttonAddQuestion;
    AppCompatButton box1, box2, box3, box4;
    String nameOfQuiz, numberOfQuestions, finalStringForFile = "";
    String answer1, answer2, answer3, answer4, question;
    NameOfKviz nameOfKvizClass;
    int counterOfCurrentQuestion = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_to_sqlite);

        initialization(); //Inicijalizacija view elemenata iz Layouta.
        dialogMethod(); // //Dialog i pisanje informacija (ime i broj pitanja za kviz.)

        buttonAddQuestion.setOnClickListener(view -> buttonAddQuestionLogic()); //metoda kada se klikne dugme Add.

        //buttoni iz dialoga
        buttonOkFromDialog.setOnClickListener(view -> buttonDialog());
        buttonCancelFromDialog.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        box1.setOnClickListener(view -> buttonBox1Logic());
        box2.setOnClickListener(view -> buttonBox2Logic());
        box3.setOnClickListener(view -> buttonBox3Logic());
        box4.setOnClickListener(view -> buttonBox4Logic());
    }

    //dialog
    private void dialogMethod(){
        currentQuestion.setText("1");
        dialog = new Dialog(AddToSQLite.this);
        dialog.setContentView(R.layout.custom_dialog_add_quiz);  //Postavlja nas custom dialog u objekat dialog.
        dialog.getWindow().setBackgroundDrawable(AppCompatResources.getDrawable(AddToSQLite.this, R.drawable.dialog_background));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false); // Da ne bi nestao dialog ako se klikne pored okvira dialoga dok smo u app.

        nameOfQuizFromEditText = dialog.findViewById(R.id.editTextNameOfQuiz);
        numberOfQuestionsFromEditText = dialog.findViewById(R.id.editTextNumberOfQuestion);
        buttonOkFromDialog = dialog.findViewById(R.id.buttonOkDialog);
        buttonCancelFromDialog = dialog.findViewById(R.id.buttonCancelDialog);

        dialog.show();
    }
    private void initialization(){
        nameOfKvizClass = new NameOfKviz();
        etQuestion = findViewById(R.id.question);
        etAnswer1 = findViewById(R.id.editTextAnswer1);
        etAnswer2 = findViewById(R.id.editTextAnswer2);
        etAnswer3 = findViewById(R.id.editTextAnswer3);
        etAnswer4 = findViewById(R.id.editTextAnswer4);
        buttonAddQuestion = findViewById(R.id.addQuestionNewQuiz);
        nameOfQuizTextView = findViewById(R.id.textviewNameOfNewQuiz);
        currentQuestion = findViewById(R.id.currentQuestion);
        box1 = findViewById(R.id.box1);
        box2 = findViewById(R.id.box2);
        box3 = findViewById(R.id.box3);
        box4 = findViewById(R.id.box4);
    }

    private void buttonDialog(){
        nameOfQuiz = nameOfQuizFromEditText.getText().toString().trim();
        numberOfQuestions = numberOfQuestionsFromEditText.getText().toString().trim();
        if(!numberOfQuestions.equals("")) {
            if (Integer.parseInt(numberOfQuestions) < 5 || Integer.parseInt(numberOfQuestions) > 20) {
                Toast.makeText(this, "You must have between 5 - 20 questions!", Toast.LENGTH_SHORT).show();
            } else if (nameOfQuiz.isEmpty()) {
                Toast.makeText(this, "You must write name of quiz!", Toast.LENGTH_SHORT).show();
            } else {
                nameOfQuizTextView.setText(nameOfQuiz);
                dialog.dismiss();
            }
        }else{
            Toast.makeText(this, "You must enter some data!", Toast.LENGTH_SHORT).show();
        }
    }

    private void buttonAddQuestionLogic(){
        question = etQuestion.getText().toString().trim();
        answer1 = etAnswer1.getText().toString().trim();
        answer2 = etAnswer2.getText().toString().trim();
        answer3 = etAnswer3.getText().toString().trim();
        answer4 = etAnswer4.getText().toString().trim();
        if(blBox1 == null & blBox2 == null & blBox3 == null & blBox4 == null){
            Toast.makeText(this, "Please, check your correct answer by clicking on left box.", Toast.LENGTH_SHORT).show();
            return;
        }else{
            addStarsToAnswers();
        }
        if(!(question.equals("") || answer1.equals("") || answer2.equals("") || answer3.equals("") || answer4.equals(""))){
            finalStringForFile = finalStringForFile + (question + "\n" + answer1 + "\n" + answer2 + "\n" + answer3 + "\n" + answer4 + "\n");
            counterOfCurrentQuestion++; // brojac trenutnog pitanja za upis u bazu
            currentQuestion.setText(String.valueOf(counterOfCurrentQuestion));
            resetColorsAndEditText(); // vraca na pocetnu vrijednost svaki view u layoutu.
        }else{
            Toast.makeText(this, "Please, enter data in every field.", Toast.LENGTH_SHORT).show();
        }
        if(counterOfCurrentQuestion == (Integer.parseInt(numberOfQuestions) + 1)){ // provjerava da li je stiglo do zadnjeg pitanja ako jeste krece upis fajlova i povratak u proslu aktivnost.
            nameOfKvizClass.nameOfQuiz(nameOfQuiz.toLowerCase(), finalStringForFile ,this);
            Log.d("TAG6", numberOfQuestions.toString());
            nameOfKvizClass.nameOfQuizAppend(MainActivity.FILENAME_BEST_TRESULT,   "0/" + numberOfQuestions + "\n", this);
            nameOfKvizClass.nameOfQuizAppend(MainActivity.FILENAME_NAMES_OF_QUIZES, "\n" + nameOfQuiz, this);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    //Varijable za provjeru tacnog skora
    Boolean blBox1, blBox2, blBox3, blBox4;
    private void buttonBox1Logic(){
        blBox1 = true;
        blBox2 = false;
        blBox3 = false;
        blBox4 = false;
        box1.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.box_background_correct_answer));
        box2.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.box_background));
        box3.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.box_background));
        box4.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.box_background));
    }
    private void buttonBox2Logic(){
        blBox1 = false;
        blBox2 = true;
        blBox3 = false;
        blBox4 = false;
        box1.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.box_background));
        box2.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.box_background_correct_answer));
        box3.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.box_background));
        box4.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.box_background));
    }private void buttonBox3Logic(){
        blBox1 = false;
        blBox2 = false;
        blBox3 = true;
        blBox4 = false;
        box1.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.box_background));
        box2.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.box_background));
        box3.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.box_background_correct_answer));
        box4.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.box_background));
    }private void buttonBox4Logic(){
        blBox1 = false;
        blBox2 = false;
        blBox3 = false;
        blBox4 = true;
        box1.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.box_background));
        box2.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.box_background));
        box3.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.box_background));
        box4.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.box_background_correct_answer));
    }
    private void resetColorsAndEditText(){
        etQuestion.setText("");
        etAnswer1.setText("");
        etAnswer2.setText("");
        etAnswer3.setText("");
        etAnswer4.setText("");
        blBox1 = null;
        blBox2 = null;
        blBox3 = null;
        blBox4 = null;
        box1.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.box_background));
        box2.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.box_background));
        box3.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.box_background));
        box4.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.box_background));
    }
    private void addStarsToAnswers(){
        if(Boolean.TRUE.equals(blBox1)){
            answer1 = "*****" + answer1;
        }else if(Boolean.TRUE.equals(blBox2)){
            answer2 = "*****" + answer2;
        }else if(Boolean.TRUE.equals(blBox3)){
            answer3 = "*****" + answer3;
        }else{
            answer4 = "*****" + answer4;
        }
    }
}