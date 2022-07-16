package com.sreco.quizmobilnoracunarstvo.startquiz.game_logic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.sreco.quizmobilnoracunarstvo.MainActivity;
import com.sreco.quizmobilnoracunarstvo.R;
import com.sreco.quizmobilnoracunarstvo.file_system_base.NameOfKviz;
import com.sreco.quizmobilnoracunarstvo.startquiz.chosequizlogic.ChooseQuiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizLogic extends AppCompatActivity {

    AppCompatButton buttonOkDialog;
    Dialog dialog;
    LogicToFindAnswers logicToFindAnswers;
    CheckIsAnswerCorrect checkIsAnswerCorrect;
    NameOfKviz quiz_file, nameOfKviz; //fajl u kojem su pitanja i odgovori
    String name_txt_file, tempText;
    TextView question, scoreOfPlayer, yourScoreTextView;
    AppCompatButton answer1, answer2, answer3, answer4;
    List<String> listOfQuestionAndAnswers;
    List<Integer> listOfCorrectAnswers;
    //Ovo su linije buttun=a svakog u fajlu i dgej stoje njihovi odgovori.
    List<Integer> listOfPossibleAnswersOne = Arrays.asList(1, 6, 11, 16, 21, 26, 31, 36, 41, 46, 51, 56, 61, 66, 71, 76, 81, 86, 91, 96);
    List<Integer> listOfPossibleAnswersTwo = Arrays.asList(2, 7, 12, 17, 22, 27, 32, 37, 42, 47, 52, 57, 62, 67, 72, 77, 82, 87, 92, 97);
    List<Integer> listOfPossibleAnswersThree = Arrays.asList(3, 8, 13, 18, 23, 28, 33, 38, 43, 48, 53, 58, 63, 68, 73, 78, 83, 88, 93, 98);
    List<Integer> listOfPossibleAnswersFor = Arrays.asList(4, 9, 14, 19, 24, 29, 34, 39, 44, 49, 54, 59, 64, 69, 74, 79, 84, 89, 94, 99);
    int brojacTrenutneLinijeUFajlu = 0, resourceID, scoreTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quiz_logic);

        answer1 = findViewById(R.id.buttonAnswer1);
        answer2 = findViewById(R.id.buttonAnswer22);
        answer3 = findViewById(R.id.buttonAnswer3);
        answer4 = findViewById(R.id.buttonAnswer4);
        question = findViewById(R.id.question);
        scoreOfPlayer = findViewById(R.id.scoreOfPlayer);
        quiz_file = new NameOfKviz();
        nameOfKviz = new NameOfKviz();
        logicToFindAnswers = new LogicToFindAnswers();
        checkIsAnswerCorrect = new CheckIsAnswerCorrect();

        Bundle extra = getIntent().getExtras();
        int numberOfQuizFromListView = extra.getInt("broj_kviza"); //broj kviza i broj rezultata tog kviza.

        if(numberOfQuizFromListView < 4){ //Provjerava da li je kviz instaliran sa app, ili ubacen od strane korisnika jer nije ista logika za oba slucaja.
            name_txt_file = extra.getString("naziv_kviza_txt");
            name_txt_file = name_txt_file.toLowerCase();
            resourceID = getResources().getIdentifier(name_txt_file, "raw", getPackageName());
            listOfQuestionAndAnswers = quiz_file.readFromRaw(this, resourceID); //u listu ulaze pdoaci iz txt fajla koji je spakovan u raw.
        }else{
            name_txt_file = extra.getString("naziv_kviza_txt");
            name_txt_file = name_txt_file.toLowerCase();
            listOfQuestionAndAnswers = nameOfKviz.readQuizNamesFromBase(QuizLogic.this, name_txt_file);
        }

        listOfCorrectAnswers = logicToFindAnswers.findAnswers(listOfQuestionAndAnswers); //uzima tacnja rjesenja iz liste pomocu prvih karaktera u odgovorima "*****"
        dialogMethod(); // kreiranje dialoga, ali se ne poziva.
        yourScoreTextView = dialog.findViewById(R.id.textViewScore23);  //Tekst YourScore u dialogu.

        metodaZaSvaDugmadi(); //Ispis prvog pitanja na ekran.

        //stiskanje dugmadi u layout, kao i pregledavanje tacnih dogovora pomocu metoda.
        answer1.setOnClickListener(view -> {
            if(checkIsAnswerCorrect.checkIsAnswerCorrect(listOfPossibleAnswersOne, brojacTrenutneLinijeUFajlu, listOfCorrectAnswers)) {
                scoreTotal += 1;
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
            }
            scoreOfPlayer.setText(String.valueOf(scoreTotal));
            metodaZaSvaDugmadi();
        });
        answer2.setOnClickListener(view -> {
            if(checkIsAnswerCorrect.checkIsAnswerCorrect(listOfPossibleAnswersTwo, brojacTrenutneLinijeUFajlu, listOfCorrectAnswers)) {
                scoreTotal += 1;
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
            }
            scoreOfPlayer.setText(String.valueOf(scoreTotal));
            metodaZaSvaDugmadi();
        });
        answer3.setOnClickListener(view -> {
            if(checkIsAnswerCorrect.checkIsAnswerCorrect(listOfPossibleAnswersThree, brojacTrenutneLinijeUFajlu, listOfCorrectAnswers)) {
                scoreTotal += 1;
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
            }
            scoreOfPlayer.setText(String.valueOf(scoreTotal));
            metodaZaSvaDugmadi();
        });
        answer4.setOnClickListener(view -> {
            if(checkIsAnswerCorrect.checkIsAnswerCorrect(listOfPossibleAnswersFor, brojacTrenutneLinijeUFajlu, listOfCorrectAnswers)) {
                scoreTotal += 1;
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
            }
            scoreOfPlayer.setText(String.valueOf(scoreTotal));
            metodaZaSvaDugmadi();
        });
        ////////
        //Vracanje u ChooseQuiz klasu, nakon sto se kviz zavrsi.
        buttonOkDialog.setOnClickListener(view -> {
            int tempNumber = Integer.parseInt(yourScoreTextView.getText().toString().trim().split("\\s+")[2]);
            List<String> resultsFromFile = new ArrayList<>();
            resultsFromFile = nameOfKviz.readQuizNamesFromBase(this, MainActivity.FILENAME_BEST_TRESULT);
            String [] bestScoreAndMaxScore = resultsFromFile.get(numberOfQuizFromListView).trim().split("/");
            if(tempNumber < Integer.parseInt(bestScoreAndMaxScore[0])) {
                startActivity(new Intent(this, ChooseQuiz.class));
                finish();
            }
            else{
                resultsFromFile.set(numberOfQuizFromListView, ((tempNumber) + "/" + (bestScoreAndMaxScore[1])));
                String resultForFile = "";
                for(String value : resultsFromFile){
                    resultForFile = resultForFile + value + "\n";
                }
                nameOfKviz.nameOfQuiz(MainActivity.FILENAME_BEST_TRESULT, resultForFile, this);
                startActivity(new Intent(this, ChooseQuiz.class));
                finish();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void metodaZaSvaDugmadi(){
        boolean checkIsQuizOver = false;
        try {
            question.setText(listOfQuestionAndAnswers.get(brojacTrenutneLinijeUFajlu++));
            checkWhickIsCorrectResultAndDelitingZvjezda(brojacTrenutneLinijeUFajlu);
            answer1.setText(listOfQuestionAndAnswers.get(brojacTrenutneLinijeUFajlu++));
            checkWhickIsCorrectResultAndDelitingZvjezda(brojacTrenutneLinijeUFajlu);
            answer2.setText(listOfQuestionAndAnswers.get(brojacTrenutneLinijeUFajlu++));
            checkWhickIsCorrectResultAndDelitingZvjezda(brojacTrenutneLinijeUFajlu);
            answer3.setText(listOfQuestionAndAnswers.get(brojacTrenutneLinijeUFajlu++));
            checkWhickIsCorrectResultAndDelitingZvjezda(brojacTrenutneLinijeUFajlu);
            answer4.setText(listOfQuestionAndAnswers.get(brojacTrenutneLinijeUFajlu++));
        }catch (IndexOutOfBoundsException exception){
            checkIsQuizOver = true;
            exception.printStackTrace();
        }
        if(checkIsQuizOver){
            tempText = "Your score: " + scoreTotal;
            yourScoreTextView.setText(tempText);
            dialog.show();
            //prikazati rezultat u dijalogu i onda vratiti na main activity kada pritisne ok
        }
    }

    private void checkWhickIsCorrectResultAndDelitingZvjezda(int brojac){
        String zvjezdice = "*****";
        if(listOfQuestionAndAnswers.get(brojac).startsWith(zvjezdice)){
            Collections.replaceAll(listOfQuestionAndAnswers, listOfQuestionAndAnswers.get(brojac), listOfQuestionAndAnswers.get(brojac).substring(5, listOfQuestionAndAnswers.get(brojac).length()));
        }
    }

    private void dialogMethod(){  // Prikaz rezultata i vracanje na Choise klasu.
        dialog = new Dialog(QuizLogic.this);
        dialog.setContentView(R.layout.custom_dialog_quiz_logic);  //Postavlja nas custom dialog u objekat dialog.
        dialog.getWindow().setBackgroundDrawable(AppCompatResources.getDrawable(QuizLogic.this, R.drawable.dialog_background_yellow_border));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false); // Da ne bi nestao dialog ako se klikne pored okvira dialoga dok smo u app.

        buttonOkDialog = dialog.findViewById(R.id.buttonNoDialog);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, ChooseQuiz.class));
        super.onBackPressed();
    }
}