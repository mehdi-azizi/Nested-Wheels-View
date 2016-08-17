package com.example.ma.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.ma.nestedwheel.R;


public class MainActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_number=(Button)findViewById(R.id.bt_number_sample);
        bt_number.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN) {
                    Intent intent = new Intent(MainActivity.this, NumberSample.class);
                    startActivity(intent);
                }
                return true;
            }
        });
        Button bt_text=(Button)findViewById(R.id.bt_text_sample);
        bt_text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN) {
                    Intent intent = new Intent(MainActivity.this, TextSample.class);
                    startActivity(intent);
                }
                return true;
            }
        });

    }


}
