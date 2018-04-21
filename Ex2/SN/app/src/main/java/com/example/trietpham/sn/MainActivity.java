package com.example.trietpham.sn;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner out_spin,in_spin;
    EditText  in_edit;
    String inspin, outspin, inedit;
    TextView ret;
    Button submitbtn, resetbtn;
    float inCur;
    ArrayAdapter arrayAdapterOutput, arrayAdapterInput;
    DecimalFormat format = new DecimalFormat("#.########");
    InputMethodManager imm;
    ImageView exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        in_spin = (Spinner) findViewById(R.id.in_spinner);
        out_spin = (Spinner) findViewById(R.id.out_spinner);
        in_edit = (EditText) findViewById(R.id.in_edit);
        in_edit.setImeOptions(EditorInfo.IME_ACTION_DONE);
        ret = (TextView) findViewById(R.id.result);
        submitbtn = (Button) findViewById(R.id.exchange_button);
        resetbtn = (Button) findViewById(R.id.reset_button);
        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        exit = (ImageView)findViewById(R.id.exitbtn);
        final String[] type = {"USD","VND","EUR","JPY","KRW","GBP"};
        final ArrayList<String> arrayInput = new ArrayList<>(6);
        final ArrayList<String> arrayOutput = new ArrayList<>(5);
        for(int i = 0; i < type.length;i++){
            arrayInput.add(type[i]);
        }
        arrayAdapterInput = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,arrayInput);
        in_spin.setAdapter(arrayAdapterInput);
        //final String x = in_spin.getSelectedItem().toString();
        in_spin.setOnItemSelectedListener(this);

        for(int i = 0; i < type.length;i++){
            arrayOutput.add(type[i]);
        }
        arrayAdapterOutput = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,arrayOutput);
        out_spin.setAdapter(arrayAdapterOutput);
        out_spin.setOnItemSelectedListener(this);

        new Thread(){
            @Override
            public void run() {
                in_edit.setOnEditorActionListener(new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        if( i == EditorInfo.IME_ACTION_DONE){
                            submitbtn.performClick();
                            imm.hideSoftInputFromWindow(in_edit.getWindowToken(),0);
                            return true;
                        }
                        return false;
                    }
                });
            }
        }.start();



        new Thread(){
            @Override
            public void run() {
                submitbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        inedit = in_edit.getText().toString();
                        if(inedit.equals("")){
                            in_edit.setError("Please type properly!");
                        }
                        else{
                            inCur = Float.parseFloat(inedit);
                            Handle(inspin,outspin, inCur);
                        }
                        if(inspin.equals(outspin)){
                            if(inedit.equals("")){
                                in_edit.setError("Please type properly!");
                            }
                            else
                            ret.setText("You can't exchange two similar currency.\nPlease select two different type !!!!!");
                        }


                    }
                });
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                resetbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ret.setText("");
                        in_edit.setText("");
                    }
                });
            }
        }.start();
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(1);
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinner = (Spinner) adapterView;
        if(spinner.getId() == R.id.in_spinner){
            inspin = adapterView.getItemAtPosition(i).toString();
        }
        else if(spinner.getId() == R.id.out_spinner){
            outspin = adapterView.getItemAtPosition(i).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void Handle(String in, String out, float inCur){

        /*            USD            */

        if(in == "USD" && out == "VND"){
            String x = format.format(inCur * 23000f);
            ret.setText(x + " " + out);
        }
        if(in == "USD" && out == "EUR"){
            String x = format.format(inCur * 0.81f);
            ret.setText(x+ " " + out);
        }
        if(in == "USD" && out == "JPY"){
            String x = format.format(inCur * 107f);
            ret.setText(x+ " " + out);
        }
        if(in == "USD" && out == "KRW"){
            String x = format.format(inCur * 1067f);
            ret.setText(x+ " " + out);
        }
        if(in == "USD" && out == "GBP"){
            String x = format.format(inCur * 0.7f);
            ret.setText(x+ " " + out);
        }

        /*            VND            */

        if(in == "VND" && out == "USD"){
            String x = format.format(inCur / 23000f);
            ret.setText(x+ " " + out);
        }
        if(in == "VND" && out == "EUR"){
            String x = format.format(inCur / 28000f);
            ret.setText(x+ " " + out);
        }
        if(in == "VND" && out == "JPY"){
            String x = format.format(inCur / 212.8f);
            ret.setText(x+ " " + out);
        }
        if(in == "VND" && out == "KRW"){
            String x = format.format(inCur / 21.4f);
            ret.setText(x+ " " + out);
        }
        if(in == "VND" && out == "GBP"){
            String x = format.format(inCur / 32250f);
            ret.setText(x+ " " + out);
        }

        /*            EUR            */

        if(in == "EUR" && out == "USD"){
            String x = format.format(inCur / 0.7f);
            ret.setText(x+ " " + out);
        }
        if(in == "EUR" && out == "VND"){
            String x = format.format(inCur * 28000f);
            ret.setText(x+ " " + out);
        }
        if(in == "EUR" && out == "JPY"){
            String x = format.format(inCur * 133f);
            ret.setText(x+ " " + out);
        }
        if(in == "EUR" && out == "KRW"){
            String x = format.format(inCur * 1321f);
            ret.setText(x+ " " + out);
        }
        if(in == "EUR" && out == "GBP"){
            String x = format.format(inCur * 0.87f);
            ret.setText(x+ " " + out);
        }

        /*            JPY            */

        if(in == "JPY" && out == "USD"){
            String x = format.format(inCur / 107f);
            ret.setText(x+ " " + out);
        }
        if(in == "JPY" && out == "VND"){
            String x = format.format(inCur * 212.8f);
            ret.setText(x+ " " + out);
        }
        if(in == "JPY" && out == "EUR"){
            String x = format.format(inCur / 133f);
            ret.setText(x+ " " + out);
        }
        if(in == "JPY" && out == "KRW"){
            String x = format.format(inCur * 9.93f);
            ret.setText(x+ " " + out);
        }
        if(in == "JPY" && out == "GBP"){
            String x = format.format(inCur * 152.67f);
            ret.setText(x+ " " + out);
        }

        /*            KRW            */

        if(in == "KRW" && out == "USD"){
            String x = format.format(inCur / 1067f);
            ret.setText(x+ " " + out);
        }
        if(in == "KRW" && out == "VND"){
            String x = format.format(inCur * 24.1f);
            ret.setText(x+ " " + out);
        }
        if(in == "KRW" && out == "EUR"){
            String x = format.format(inCur / 1321f);
            ret.setText(x+ " " + out);
        }
        if(in == "KRW" && out == "JPY"){
            String x = format.format(inCur / 9.93f);
            ret.setText(x+ " " + out);
        }
        if(in == "KRW" && out == "GBP"){
            String x = format.format(inCur * 1515.15f);
            ret.setText(x+ " " + out);
        }

        /*            GBP            */

        if(in == "GBP" && out == "USD"){
            String x = format.format(inCur / 0.7f);
            ret.setText(x+ " " + out);
        }
        if(in == "GBP" && out == "VND"){
            String x = format.format(inCur * 32250f);
            ret.setText(x+ " " + out);
        }
        if(in == "GBP" && out == "EUR"){
            String x = format.format(inCur / 0.87f);
            ret.setText(x+ " " + out);
        }
        if(in == "GBP" && out == "JPY"){
            String x = format.format(inCur / 152.67f);
            ret.setText(x+ " " + out);
        }
        if(in == "GBP" && out == "KRW"){
            String x = format.format(inCur / 1515.15f);
            ret.setText(x+ " " + out);
        }

    }
}
