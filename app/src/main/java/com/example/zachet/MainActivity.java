package com.example.zachet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    float val1=0, val2=0, result=0;
    String[] operations = { "+", "-", "/", "*"};

    private void Calc(Spinner spinner, TextView res) {
        String text = spinner.getSelectedItem().toString();
        if (text.equals("+")){
            result = val1 + val2;
        }
        else if (text.equals("-")){
            result = val1 - val2;
        }
        else if (text.equals("/")){
            result = val1 / val2;
        }
        else if (text.equals("*")){
            result = val1 * val2;
        }
        res.setText("" + result);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView res = findViewById(R.id.txtAnswer);
        EditText num1 = (EditText) findViewById(R.id.txtFirstNumber);
        EditText num2 = (EditText) findViewById(R.id.txtSecondNumber);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, operations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        num1.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                try {
                    val1 = Float.parseFloat(String.valueOf(s));
                } catch (final NumberFormatException e) {
                }
                Calc(spinner, res);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
        num2.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                // Прописываем то, что надо выполнить после изменения текста
                try {
                    val2 = Float.parseFloat(String.valueOf(s));
                } catch (final NumberFormatException e) {
                }
                Calc(spinner, res);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Calc(spinner, res);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);
    }

}
