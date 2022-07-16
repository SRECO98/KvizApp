package com.sreco.quizmobilnoracunarstvo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.sreco.quizmobilnoracunarstvo.file_system_base.NameOfKviz;
import com.sreco.quizmobilnoracunarstvo.make_your_quiz.AddToSQLite;
import com.sreco.quizmobilnoracunarstvo.startquiz.chosequizlogic.ChooseQuiz;

public class MainActivity extends AppCompatActivity {

    AppCompatButton buttonStart, buttonAddQuestion, buttonExitGame, buttonNoDialog, buttonYesDialog;
    Dialog dialog;
    public static final String FILENAME_NAMES_OF_QUIZES = "names_of_quiz";
    public static final String FILENAME_BEST_TRESULT = "results_of_quiz";
    String defaultQuizName = "Geography\nHistory\nMath\nInformatics";
    String startingPointsOfEveryQuiz = "0/10\n0/10\n0/10\n0/10\n";
    NameOfKviz nameOfKviz = new NameOfKviz();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hiding status bar (vrijeme, baterija, signal...)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        buttonStart = findViewById(R.id.buttonStart);
        buttonAddQuestion = findViewById(R.id.buttonAddQuestion);
        buttonExitGame = findViewById(R.id.buttonExitGame);


        //Provjerava da li file postoji vec u sistemu, da ne bi pravilo svaki put ponovo.

        if(!nameOfKviz.checkIfFileExist(this, FILENAME_NAMES_OF_QUIZES)){
            nameOfKviz.nameOfQuiz(FILENAME_NAMES_OF_QUIZES, defaultQuizName, this);
        }

        if(!nameOfKviz.checkIfFileExist(this, FILENAME_BEST_TRESULT)){
            nameOfKviz.nameOfQuiz(FILENAME_BEST_TRESULT, startingPointsOfEveryQuiz, this);
        }

        /*File file = new File(getApplicationContext().getFilesDir(), FILENAME);
        if(!file.exists()){
            nameOfQuiz(FILENAME, defaultQuizName);
        }*/
        //

        dialogMethod();

        //Opening new activities.
        buttonStart.setOnClickListener(view -> {
            startActivity(new Intent(this, ChooseQuiz.class));
            finish();
        });
        buttonAddQuestion.setOnClickListener(view -> {
            startActivity(new Intent(this, AddToSQLite.class));
            finish();
        });

        //Otvaranje dialoga za ExitGame.
        buttonExitGame.setOnClickListener(view -> dialog.show());

        // Algoritam za dugmadi u dialogu.
        buttonYesDialog.setOnClickListener(view -> {
            finish();
            System.exit(0);
        });
        buttonNoDialog.setOnClickListener(view -> dialog.dismiss());
        //
    }

    @Override
    public void onBackPressed() {
        dialog.show();
    }

    private void dialogMethod(){
        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.custom_dialog_main);  //Postavlja nas custom dialog u objekat dialog.
        dialog.getWindow().setBackgroundDrawable(AppCompatResources.getDrawable(MainActivity.this, R.drawable.dialog_background));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false); // Da ne bi nestao dialog ako se klikne pored okvira dialoga dok smo u app.

        buttonNoDialog = dialog.findViewById(R.id.buttonNoDialog);
        buttonYesDialog = dialog.findViewById(R.id.buttonYesDialog);
    }
}