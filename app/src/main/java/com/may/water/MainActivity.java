package com.may.water;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity<prublic> extends AppCompatActivity {

    private EditText edmonth;
    private Button calculate;
    private Switch sw;
    boolean isNext = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edmonth = findViewById(R.id.month);
        calculate = findViewById(R.id.button);
        sw = findViewById(R.id.sw);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    calculate();
            }
        });

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isNext =isChecked;
                TextView type = findViewById(R.id.type);
                sw.setText(isNext ? getString(R.string.every_other_month) : getString(R.string.monthly));
                type.setText(isNext?getString(R.string.every_other_month):getString(R.string.monthly));

            }
        });
    }

    public void calculate(){
        String title=getString(R.string.monthly);
        String message=getString(R.string.fee);
        int degree=0;
        float supplement=0;
        float k=0;
        float price=0;
        DialogInterface.OnClickListener listener=new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                edmonth.setText("");
            }
        };
        if(TextUtils.isEmpty(edmonth.getText())){
            title=getString(R.string.error);
            message=getString(R.string.no_input);
        }else if(!isNext){
            degree= Integer.parseInt(edmonth.getText().toString());
            if(degree<=10){
                k=7.35f;
                supplement=0;
            }else if(degree<=30){
                k=9.45f;
                supplement=21;
            }else if(degree<=50){
                k=11.55f;
                supplement=84;
            }else{
                k=12.075f;
                supplement=110.25f;
            }
            price=degree*k-supplement;
            message=getString(R.string.fee)+price;
        }else{
            title=getString(R.string.every_other_month);
            degree= Integer.parseInt(edmonth.getText().toString());
            if(degree<=20){
                k=7.35f;
                supplement=0;
            }else if(degree<=60){
                k=9.45f;
                supplement=42;
            }else if(degree<=100){
                k=11.55f;
                supplement=168;
            }else{
                k=12.075f;
                supplement=220.5f;
            }
            price=degree*k-supplement;
            message=getString(R.string.fee)+price;
        }
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra(getString(R.string.extra_price), price);
            startActivity(intent);
//           new AlertDialog.Builder(MainActivity.this)
//                .setTitle(title)
//                .setMessage(getString(R.string.fee) + message)
//                .setPositiveButton(getString(R.string.ok),listener)
//                .show();
//
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}





