package com.example.ma.sample;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.ma.nestedwheel.R;
import com.ma.nestedwheels.NestedWheelsView;

import java.util.ArrayList;

public class TextSample extends AppCompatActivity {
    TextView outer_txt;
    TextView inner_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_sample);
        outer_txt=(TextView)findViewById(R.id.outer_degree1);
        inner_txt=(TextView)findViewById(R.id.inner_degree1);
        initWheels();
    }
    private void initWheels() {

        Paint paint1 = new Paint();
        paint1.setColor(Color.RED);

        Paint paint2 = new Paint();
        paint2.setColor(Color.GREEN);


        ArrayList<String> texts = new ArrayList<>();
        texts.add("Argentina");
        texts.add("Australia");
        texts.add("Canada");
        texts.add("China");
        texts.add("Egypt");
        texts.add("France");
        texts.add("Germany");
        texts.add("Italy");
        ArrayList<Paint> paints = new ArrayList<>();
        paints.add(paint1);
        paints.add(paint2);
        paints.add(paint1);
        paints.add(paint2);
        paints.add(paint1);
        paints.add(paint2);
        paints.add(paint1);
        paints.add(paint2);



        Paint paint3 = new Paint();
        paint3.setColor(Color.GREEN);

        Paint paint4 = new Paint();
        paint4.setColor(Color.RED);

        Paint paint5 = new Paint();
        paint5.setColor(Color.YELLOW);

        Paint paint6 = new Paint();
        paint6.setColor(0xffdadada);

        ArrayList<String> texts1 = new ArrayList<>();
        texts1.add("Spring");
        texts1.add("Summer");
        texts1.add("Fall");
        texts1.add("Winter");
        ArrayList<Paint> paints1 = new ArrayList<>();
        paints1.add(paint3);
        paints1.add(paint4);
        paints1.add(paint5);
        paints1.add(paint6);
        NestedWheelsView myView = (NestedWheelsView) findViewById(R.id.mySpinner1);
        myView.init(texts, texts1, paints, paints1);



        Paint textPaint=new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(20f);
        myView.setText_paint_outer(textPaint);




        myView.setOnRotateListener(new NestedWheelsView.onRotateListener() {
            @Override
            public void onRotating(NestedWheelsView.RotatedObject wheelName, double degree, String selected) {
                if(wheelName== NestedWheelsView.RotatedObject.InnerWheel)
                {
                    inner_txt.setText("Inner Wheel      degree="+degree+"°    selected="+selected);
                }
                else
                {
                    outer_txt.setText("Outer Wheel      degree="+degree+"°    selected="+selected);
                }

            }

            @Override
            public void onRotateFinished(NestedWheelsView.RotatedObject wheelName, double degree, String selected) {
                Log.v(">>>>>>>","onRotate finished. "+" deg="+degree+"  selected="+selected);
            }
        });



    }

}
