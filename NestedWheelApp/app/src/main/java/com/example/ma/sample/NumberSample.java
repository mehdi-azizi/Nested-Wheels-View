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

public class NumberSample extends AppCompatActivity {

    TextView outer_txt;
    TextView inner_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_sample);


        outer_txt=(TextView)findViewById(R.id.outer_degree);
        inner_txt=(TextView)findViewById(R.id.inner_degree);
        initWheels();
    }

    private void initWheels() {

        Paint paint1 = new Paint();
        paint1.setColor(Color.GRAY);

        Paint paint2 = new Paint();
        paint2.setColor(Color.WHITE);

        ArrayList<String> texts = new ArrayList<>();
        texts.add("0");
        texts.add("1");
        texts.add("2");
        texts.add("3");
        texts.add("4");
        texts.add("5");
        texts.add("6");
        texts.add("7");
        texts.add("8");
        texts.add("9");

        ArrayList<Paint> paints = new ArrayList<>();
        paints.add(paint1);
        paints.add(paint2);
        paints.add(paint1);
        paints.add(paint2);
        paints.add(paint1);
        paints.add(paint2);
        paints.add(paint1);
        paints.add(paint2);
        paints.add(paint1);
        paints.add(paint2);

        ArrayList<String> texts1 = new ArrayList<>();
        texts1.add("0");
        texts1.add("1");
        texts1.add("2");
        texts1.add("3");
        texts1.add("4");
        texts1.add("5");
        texts1.add("6");
        texts1.add("7");
        texts1.add("8");
        texts1.add("9");
        ArrayList<Paint> paints1 = new ArrayList<>();
        paints1.add(paint2);
        paints1.add(paint1);
        paints1.add(paint2);
        paints1.add(paint1);
        paints1.add(paint2);
        paints1.add(paint1);
        paints1.add(paint2);
        paints1.add(paint1);
        paints1.add(paint2);
        paints1.add(paint1);

        NestedWheelsView myView = (NestedWheelsView) findViewById(R.id.mySpinner);
        myView.init(texts, texts1, paints, paints1);






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
