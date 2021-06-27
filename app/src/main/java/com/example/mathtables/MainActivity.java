package com.example.mathtables;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    private ListView tables;
    private SeekBar number;
    private EditText numberDisplay;
    int numberEntered;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tables = (ListView) findViewById(R.id.table);
        number = (SeekBar) findViewById(R.id.seekBar);
        numberDisplay = (EditText) findViewById(R.id.editNumber);

        number.setMin(1);
        number.setMax(1000);

        seekBarProgress(500);
        number.setProgress(500);
        numberDisplay.setText(500+"");
        numberEntered = Integer.parseInt(numberDisplay.getText().toString());
        seekBarProgress(numberEntered);

        number.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBarProgress(i);
                numberDisplay.setText(i+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                tables.animate().alpha(0).setDuration(0);

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                tables.animate().alpha(1).setDuration(800);

            }
        });
    }

    private void seekBarProgress(int i){
        ArrayList<String> result = new ArrayList<String>();
        for(int j=1;j<=10;j++) {
            result.add(i+" X "+j+" = "+i*j);
        }
        ArrayAdapter<String> resultAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,result);
        tables.setAdapter(resultAdapter);
    }
}