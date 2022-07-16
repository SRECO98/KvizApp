package com.sreco.quizmobilnoracunarstvo.file_system_base;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.google.android.material.snackbar.Snackbar;
import com.sreco.quizmobilnoracunarstvo.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class NameOfKviz {

    List <String> list = new ArrayList<>();

    //pravi novi fajl u sistemu.
    public void nameOfQuiz(String fileName, String content, Context context){
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(fileName, Context.MODE_PRIVATE); //sadrzaj se dodaje na kraj postojeceg append// private pravi novi fajl uvijek
            fos.write(content.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //pravi novi fajl u sistemu.
    public void nameOfQuizAppend(String fileName, String content, Context context){
        FileOutputStream fos = null;

        try {
            fos = context.openFileOutput(fileName, Context.MODE_APPEND); //sadrzaj se dodaje na kraj postojeceg append// private pravi novi fajl uvijek
            fos.write(content.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<String> readQuizNamesFromBase(Context context, String fileName){
        list = new ArrayList<>();
        FileInputStream fis = null;

        try{
            fis = context.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);

            String line;

            while( (line = bufferedReader.readLine()) != null){
                list.add(line);
            }
        }catch (IOException exc){
            exc.printStackTrace();
        }finally {
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    //provjerava da li postoji file u filesistemu
    public boolean checkIfFileExist(Context context, String fileName){
        String path = context.getFilesDir().getAbsolutePath();
        File file = new File(path + "/" + fileName);
        return file.exists();
    }

    public List<String> readFromRaw (Context context, int file_id) {
        String line;
        List<String> list = new ArrayList<>();
        try {
            InputStream file = context.getResources().openRawResource(file_id);
            BufferedReader br = new BufferedReader(new InputStreamReader(file));
            line = br.readLine();
            while (line != null) {
                list.add(line);
                line = br.readLine();
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        return list;
    }
}
