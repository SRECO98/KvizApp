package com.sreco.quizmobilnoracunarstvo.startquiz.chosequizlogic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sreco.quizmobilnoracunarstvo.R;

import java.util.List;

public class CustomBaseAdapterResult extends BaseAdapter {

    Context context;
    List<String> resultOfQuiz;
    LayoutInflater layoutInflater;

    public CustomBaseAdapterResult(Context context, List <String> resultOfQuiz){
        this.context = context;
        this.resultOfQuiz = resultOfQuiz;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return resultOfQuiz.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.custom_list_view_results, viewGroup, false);
        TextView textView = view.findViewById(R.id.textviewResultsOfQuiz);
        textView.setText(resultOfQuiz.get(i));
        return view;
    }
}