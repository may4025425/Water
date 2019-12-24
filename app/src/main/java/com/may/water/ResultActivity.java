package com.may.water;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private static final String TAG = ResultActivity.class.getSimpleName();//用public也可以，private static final不打也可以，只是好辨認其(TAG)用途
    private static final float DEFAULT_FEE = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        //float price = intent.getFloatExtra(getString(R.string.extra_price),-1);
        float price = intent.getFloatExtra(getString(R.string.extra_price),DEFAULT_FEE);

        Log.d(TAG,price + "");
        //Log.d("ResultActivity",price + "");
        TextView edprice = findViewById(R.id.price);

        int n = (int)(price + 0.5f);//整數的四捨五入
        edprice.setText(n+ "");
    }
}
