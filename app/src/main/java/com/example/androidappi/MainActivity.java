package com.example.androidappi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    Context context = null;


    private EditText input, fileName;
    private String text, fileToRead, fileToWrite;
    private StringBuilder textFromFile = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = findViewById(R.id.editText);
        fileName = findViewById(R.id.fileName);
        context = MainActivity.this;
    }


    public void readFile(View v){
        text = input.getText().toString();
        fileToRead = fileName.getText().toString();

        try{
            input.setText("");
            InputStream ins = context.openFileInput(fileToRead);
            BufferedReader br = new BufferedReader( new InputStreamReader(ins));
            String s = "";

            while(( s=br.readLine() )!= null){
                textFromFile.append(s);
            }
            String fileAsString = textFromFile.toString();
            input.setText(fileAsString);
            ins.close();
            fileName.setText("");
        }catch(IOException e){
            Log.e("IOException", "Virhe syötteessä");
        }

    }
    public void writeFile(View v){
        text = input.getText().toString();
        fileToWrite = fileName.getText().toString();

        try{
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput(fileToWrite, Context.MODE_PRIVATE));
            ows.write(text);
            ows.close();
            fileName.setText("");
            input.setText("");
        }catch(IOException e){
            Log.e("IOException", "Virhe syötteessä");
        }
    }
}

