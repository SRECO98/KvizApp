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

public class CustomBaseAdapter extends BaseAdapter {

    Context context;
    List<String> nameOfQuiz;
    LayoutInflater layoutInflater;

    public CustomBaseAdapter(Context context, List <String> nameOfQuiz){
        this.context = context;
        this.nameOfQuiz = nameOfQuiz;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return nameOfQuiz.size();
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
        view = LayoutInflater.from(context).inflate(R.layout.activity_custom_list_view, viewGroup, false);
        TextView textView = view.findViewById(R.id.textviewNameOfQuiz);
        textView.setText(nameOfQuiz.get(i));
        return view;
    }
}