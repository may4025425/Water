package com.may.water;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity<prublic> extends AppCompatActivity {

    private EditText edmonth;
    private EditText ednext;
    private Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edmonth = findViewById(R.id.month);
        ednext = findViewById(R.id.next);
        calculate = findViewById(R.id.button);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });//這也是匿名類別，故MainActivity目前有兩個匿名類別

//        calbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                    calculate();
//            }
//        });
    }

    public void calculate(View view){
        /*有兩種呼叫匿名類別的方式：
        第一種是將方法直接寫在ｏｎｃｌｉｃｋ(這個匿名類別裡），
        第二種是將方法而外寫，然後在外面建一個方法，只需在ｏｎｃｌｉｃｋ中呼叫那方法即可，
        注意：如果已在onclick中呼叫方法calculate()，那麼下面的public void calculate(View v){.....}其（）內的View v 要刪掉*/
        /*
             String num = String.valueOf(edmonth.getText().toString().isEmpty());
             String num2 = String.valueOf(ednext.getText().toString().isEmpty());
             錯誤的地方...why?
             Ans:既然都同樣適用於判斷要計算的標準，就不必再設定兩種變數（num、num2）了，還有isEmpty()裡要判斷的東西你也沒給，不！你給了！但你竟然把他寫在括號外面
       */

        /* 將文字做是否空值判斷，他的屬性當然是字串:TextUtils.isEmpty(java.lang.String)
           boolean num = TextUtils.isEmpty(edmonth.getText().toString());
           boolean num2 = TextUtils.isEmpty(ednext.getText().toString());
           因為是判斷 ”是否“ 故使用boolean值， 你就可以直接寫成這樣：
           if(num&&num2){

           }else if(!num){

            }else{

             } */

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                edmonth.setText("");
                ednext.setText("");
            }
        };
        float supplement;
        float price = 0; //要用它計算時就得設初值，不然會出錯
        String title = "每月抄表費用";//上面有設值，下面settitle就不用設了，下面的message也是，如果要寫很多類似功能，但title都不一樣，就可以先不設值，只改下面的title內容即可
        String message ;

        if(TextUtils.isEmpty(edmonth.getText())&&TextUtils.isEmpty(ednext.getText())){ //發現：如果嫌棄TextUtils礙眼，可以利用電燈泡將其加到import中
            //沒有語法問題，不需打上toString()
            title = "錯誤";
            message = "無法計算";

        }else if(!TextUtils.isEmpty(edmonth.getText())){
            float useddegrees = Float.parseFloat(edmonth.getText().toString());
            /*
            這裡的toString純粹是因為語法的問題，parseFloat(java.lang.String)，故要馬你多加一個.toString()，
            要馬就是打成 float useddegrees = Float.parseFloat(String.valueOf(edmonth.getText()));
                                                                                             */
            if(useddegrees <11){
                supplement = 0 ;
                price = useddegrees * 7.35f + supplement;
            }else if (useddegrees < 31){
                supplement = 21 ;
                price = useddegrees * 9.45f + supplement;
            }else if (useddegrees < 51){
                supplement = 84 ;
                price = useddegrees * 11.55f + supplement;

            }else {
                supplement = 110.25f ;
                price = useddegrees * 12.075f + supplement;
            }
            message = String.valueOf(price);
        }else{
            float useddegrees = Float.parseFloat(ednext.getText().toString());
            if(useddegrees <11){
                supplement = 0 ;
                price = useddegrees * 7.35f + supplement;
            }else if (useddegrees < 31){
                supplement = 42 ;
                price = useddegrees * 9.45f + supplement;
            }else if (useddegrees < 51){
                supplement = 168 ;
                price = useddegrees * 11.55f + supplement;

            }else {
                supplement = 220.5f ;
                price = useddegrees * 12.075f + supplement;
            }
            message = String.valueOf(price);
        }

        Intent intent = new Intent(this,ResultActivity.class);
        intent.putExtra(getString(R.string.extra_price),price);//相當於帶有price標籤的瓶子
        startActivity(intent);
        /*在匿名類別裡用this，但你要呼叫外部類別時要注意*/


//           new AlertDialog.Builder(MainActivity.this)
//                .setTitle(title)
//                .setMessage(getString(R.string.fee) + message)  //字串提取
//                .setPositiveButton(getString(R.string.ok),listener) //字串提取
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
