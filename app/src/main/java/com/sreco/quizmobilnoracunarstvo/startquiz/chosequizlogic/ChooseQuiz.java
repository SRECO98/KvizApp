package com.sreco.quizmobilnoracunarstvo.startquiz.chosequizlogic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.sreco.quizmobilnoracunarstvo.MainActivity;
import com.sreco.quizmobilnoracunarstvo.R;
import com.sreco.quizmobilnoracunarstvo.file_system_base.NameOfKviz;
import com.sreco.quizmobilnoracunarstvo.startquiz.game_logic.QuizLogic;

import java.util.List;

public class ChooseQuiz extends AppCompatActivity {

    List<String> listOfQuizNames, listOfResultsOfQuiz;
    NameOfKviz nameOfKviz = new NameOfKviz();
    NameOfKviz nameOfKviz2 = new NameOfKviz();
    ListView listViewNames, listViewResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hiding status bar (vrijeme, baterija, signal...)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_chose_quiz);

        listOfQuizNames = nameOfKviz.readQuizNamesFromBase(getApplicationContext(), MainActivity.FILENAME_NAMES_OF_QUIZES);
        //Ovdje mora ici drugi objekat nameOfKviz2 jer iz nekog razloga ako bude isti, samo se nastavlja ispisivati na prvi objekat.
        listOfResultsOfQuiz = nameOfKviz2.readQuizNamesFromBase(getApplicationContext(), MainActivity.FILENAME_BEST_TRESULT);

        listViewNames = findViewById(R.id.customListviewNames);
        listViewResults = findViewById(R.id.customListviewResults);

        //pozivanje listview adaptera i namjestanje (dva adaptera u jednoj aktivnosti.)
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(), listOfQuizNames);
        listViewNames.setAdapter(customBaseAdapter);
        CustomBaseAdapterResult customBaseAdapterResult2 = new CustomBaseAdapterResult(getApplicationContext(), listOfResultsOfQuiz);
        listViewResults.setAdapter(customBaseAdapterResult2);

        listViewNames.setOnItemClickListener((parent, view, position, id) -> {
            TextView textView = view.findViewById(R.id.textviewNameOfQuiz); // lociranje na kliknuti view u listView
            String text = textView.getText().toString().trim(); // uzimanja naziva kviza.

            Intent intent = new Intent(getApplicationContext(), QuizLogic.class);
            intent.putExtra("naziv_kviza_txt", text); //slanje naziva kviza
            intent.putExtra("broj_kviza", position); //slanje broja kviza u fajlu
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}