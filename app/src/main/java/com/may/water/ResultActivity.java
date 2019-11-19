package com.may.water;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        float price = intent.getFloatExtra("price",-1);
        Log.d("ResultActivity",price + "");
        TextView edprice = findViewById(R.id.price);
        int n = (int)(price + 0.5f);//整數的四捨五入
        edprice.setText(n+ "");
    }
}
