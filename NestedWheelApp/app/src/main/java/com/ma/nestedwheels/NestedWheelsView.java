package com.ma.nestedwheels;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mehdi Azizi on 8/15/2016.
 */
public class NestedWheelsView extends View implements View.OnTouchListener
{
    // <editor-fold defaultstate="collapsed" desc="Enum">
    public enum RotatedObject
    {
        OuterWheel,
        InnerWheel
    };
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Field Members">
    public static int FRAME_CIRCLE_THICKNESS=5;

    private boolean AUTO_ARC_THICKNESS = true;
    private Paint text_paint_outer;
    private Paint text_paint_inner;
    private Paint frame_circle_paint;
    private int arc_thickness = 0;
    private int witchCircle = 0;
    private boolean _hasArrowHead =true;

    private ArrayList<String> texts = new ArrayList<>();
    private ArrayList<Paint> paints = new ArrayList<>();

    private ArrayList<String> texts1 = new ArrayList<>();
    private ArrayList<Paint> paints1 = new ArrayList<>();

    private onRotateListener _rotateListener;

    private RectF rect;

    private RectF rect1;

    double lastAlfa = 0;

    float rotate = 0;
    float rotate1 = 0;

    private int mRadius = 140;
    private int mRadius1 = 80;

    int h = 0;
    int w = 0;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="View implemented Methods">
    public NestedWheelsView(Context context) {
        super(context);

        resetSize();
        initDefault();
        setOnTouchListener(this);
    }

    public NestedWheelsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDefault();
        resetSize();

        setOnTouchListener(this);
    }

    public NestedWheelsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDefault();
        resetSize();

        setOnTouchListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // drawing out circle
        for (int i = 0; i < texts.size(); i++) {
            Path tem = new Path();
            tem.addArc(rect, getDeg(i * (360 / texts.size())), (360 / texts.size()));

            Paint paint_tem = paints.get(i);

            paint_tem.setStrokeWidth(arc_thickness);
            paint_tem.setAntiAlias(true);
            paint_tem.setStrokeCap(Paint.Cap.BUTT);
            paint_tem.setStyle(Paint.Style.STROKE);

            canvas.drawPath(tem, paint_tem);
            canvas.drawTextOnPath(texts.get(i), tem, 0.0f, 0.0f, text_paint_outer);

        }

        // drawing inside circle
        for (int i = 0; i < texts1.size(); i++) {

            Path tem = new Path();
            tem.addArc(rect1, getDeg1(i * (360 / texts1.size())), (360 / texts1.size()));
            Paint paint_tem = paints1.get(i);

            paint_tem.setStrokeWidth(arc_thickness);
            paint_tem.setAntiAlias(true);
            paint_tem.setStrokeCap(Paint.Cap.BUTT);
            paint_tem.setStyle(Paint.Style.STROKE);

            canvas.drawPath(tem, paint_tem);
            canvas.drawTextOnPath(texts1.get(i), tem, 0.0f, 0.0f, text_paint_inner);
        }

        // drawing Indicator and frame circle
        Point a=new Point(w/ 2+mRadius+(arc_thickness/2),h/2);
        Point b=new Point(a.x,a.y+5);
        Point c=new Point(a.x-7,a.y+3);
        Path sour=new Path();
        sour.addCircle(w/ 2, h/2  ,mRadius+(arc_thickness/2)+FRAME_CIRCLE_THICKNESS,Path.Direction.CW);

        if(_hasArrowHead) {
            sour.setFillType(Path.FillType.EVEN_ODD);
            sour.lineTo(b.x, b.y);
            sour.lineTo(c.x, c.y);
            sour.lineTo(a.x, a.y);
            sour.close();
        }

        canvas.drawPath(sour, frame_circle_paint);

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        float dx = event.getX() - (w / 2);
        float dy = event.getY() - (h / 2);
        double r = Math.sqrt((dx * dx) + (dy * dy));

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                if (r > mRadius - arc_thickness / 2 && r < mRadius + arc_thickness / 2) {
                    witchCircle = 1;
                } else if (r > mRadius1 - arc_thickness / 2 && r < mRadius1 + arc_thickness / 2) {
                    witchCircle = 2;
                }
                lastAlfa = Math.atan2(dy, dx);
                break;
            case MotionEvent.ACTION_UP:
                if (witchCircle == 1) {
                    fireOnRotate(RotatedObject.OuterWheel,Math.round(rotate), getOutterSelected(),true);
                }
                else if (witchCircle == 2)
                {
                    fireOnRotate(RotatedObject.InnerWheel,Math.round(rotate1), getInnerSelected(),true);
                }
                witchCircle = 0;
                lastAlfa = 0;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                witchCircle = 0;
                lastAlfa = 0;
                break;
            case MotionEvent.ACTION_MOVE:

                double a = Math.atan2(dy, dx);
                if (witchCircle == 1) {

                    if (r > mRadius - arc_thickness / 2 && r < mRadius + arc_thickness / 2) {
                        rotate += (-(lastAlfa-a)*(360/3.14));
                         if(rotate<0)rotate=359;
                        rotate=rotate%360;

                        fireOnRotate(RotatedObject.OuterWheel,Math.round(rotate), getOutterSelected(),false);
                    }
                    lastAlfa = a;
                } else if (witchCircle == 2) {
                    if (r > mRadius1 - arc_thickness / 2 && r < mRadius1 + arc_thickness / 2) {
                        rotate1 += (-(lastAlfa-a)*(360/3.14));
                        if(rotate1<0)rotate1=359;
                        rotate1=rotate1%360;
                        fireOnRotate(RotatedObject.InnerWheel,Math.round(rotate1), getInnerSelected(),false);
                    }
                    lastAlfa = a;
                }
                break;
        }
        this.invalidate();
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        resetSize();
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">

    private void initDefault() {

        text_paint_outer = new Paint();
        text_paint_outer.setColor(Color.BLACK);
        text_paint_outer.setAntiAlias(true);
        text_paint_outer.setTextAlign(Paint.Align.CENTER);
        text_paint_outer.setTextSize(20f);


        text_paint_inner = new Paint();
        text_paint_inner.setColor(Color.BLACK);
        text_paint_inner.setAntiAlias(true);
        text_paint_inner.setTextAlign(Paint.Align.CENTER);
        text_paint_inner.setTextSize(20f);


        frame_circle_paint =new Paint(Paint.ANTI_ALIAS_FLAG);
        frame_circle_paint.setColor(0xffe0dfdb);

        frame_circle_paint.setStrokeWidth(FRAME_CIRCLE_THICKNESS);
        frame_circle_paint.setAntiAlias(true);
        frame_circle_paint.setStrokeCap(Paint.Cap.BUTT);
        frame_circle_paint.setStyle(Paint.Style.STROKE);
    }


    private void fireOnRotate(RotatedObject wheelName,double degree,String selected,boolean isRotatingFinished)
    {
        if(_rotateListener!=null)
        {
            if(isRotatingFinished)
            {
                _rotateListener.onRotateFinished(wheelName,degree,selected);
            }
            else
            {
                _rotateListener.onRotating(wheelName,degree,selected);
            }
        }
    }

    private void resetSize() {
        h = this.getHeight();
        w = this.getWidth();
        if (AUTO_ARC_THICKNESS) {
//            arc_thickness = (Math.min(h, w) / 6);
            arc_thickness=(mRadius-mRadius1);

        }


        rect = new RectF();
        rect.set(getWidth() / 2 - mRadius, getHeight() / 2 - mRadius, getWidth() / 2 + mRadius, getHeight() / 2 + mRadius);

        rect1 = new RectF();
        rect1.set(getWidth() / 2 - mRadius1, getHeight() / 2 - mRadius1, getWidth() / 2 + mRadius1, getHeight() / 2 + mRadius1);

    }

    private int getDeg(int number) {
        int rot = (Math.round(rotate-(180/texts.size()))) % 360;
        return (number + rot) % 360;

    }

    private int getDeg1(int number) {
        int rot = (Math.round(rotate1-(180/texts.size())) ) % 360;
        return (number + rot) % 360;

    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">
    public void init(List<String> firstCircleText, List<String> secondCircleText,
                     List<Paint> firstCircleColor, List<Paint> secondCircleColor) throws IllegalArgumentException {
        if (firstCircleColor.size() < firstCircleText.size() ||
                secondCircleColor.size() < secondCircleText.size()
                ) {
            throw new IllegalArgumentException("Color list size and Text list size doesn't math .");
        }

        texts.addAll(firstCircleText);
        paints.addAll(firstCircleColor);
        for (Paint p : paints) {
            p.setAntiAlias(true);
            p.setStrokeCap(Paint.Cap.BUTT);
            p.setStyle(Paint.Style.STROKE);
        }
        texts1.addAll(secondCircleText);
        paints1.addAll(secondCircleColor);
        for (Paint p : paints1) {
            p.setAntiAlias(true);
            p.setStrokeCap(Paint.Cap.BUTT);
            p.setStyle(Paint.Style.STROKE);
        }
    }


    public String getOutterSelected()
    {
        int selected=Math.round((360-rotate)/(360/texts.size()));
        if(selected>-1&&selected<texts.size()) {
            return texts.get(selected);
        }
        else if(texts.size()>0)
        {
            return  texts.get(0);
        }
        return null;
    }

    public String getInnerSelected()
    {
        int selected=Math.round((360-rotate1)/(360/texts1.size()));
        if(selected>-1&&selected<texts1.size()) {
            return texts1.get(selected);
        }
        else if(texts1.size()>0)
        {
          return   texts1.get(0);
        }
        return null;
    }

    public void setOnRotateListener(onRotateListener listner)
    {
    _rotateListener=listner;
    }

    public Paint getText_paint_outer() {
        return text_paint_outer;
    }

    public void setText_paint_outer(Paint text_paint_outter) {
        this.text_paint_outer = text_paint_outter;
        this.text_paint_outer.setTextAlign(Paint.Align.CENTER);
        this.text_paint_outer.setStyle(Paint.Style.FILL_AND_STROKE);

    }

    public Paint getText_paint_inner() {
        return text_paint_inner;
    }

    public void setText_paint_inner(Paint text_paint_inner) {
        this.text_paint_inner = text_paint_inner;
        this.text_paint_outer.setTextAlign(Paint.Align.CENTER);
        this.text_paint_outer.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public int getRadius() {
        return mRadius;
    }

    public void setRadius(int mRadius) {
        this.mRadius = mRadius;
    }

    public int getRadius1() {
        return mRadius1;
    }

    public void setRadius1(int mRadius1) {
        this.mRadius1 = mRadius1;
    }

    public int getArc_thickness() {
        return arc_thickness;
    }

    public void setArc_thickness(int arc_thickness) {
        AUTO_ARC_THICKNESS = false;
        this.arc_thickness = arc_thickness;
        resetSize();
    }

    public boolean hasArrowHead() {
        return _hasArrowHead;
    }

    public void setArrowHead(boolean _hasIndicator) {
        this._hasArrowHead = _hasIndicator;
    }


    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Listener Interface">
    public interface onRotateListener
    {
        void onRotating(RotatedObject wheelName,double degree,String selected);
        void onRotateFinished(RotatedObject wheelName,double degree,String selected);
    }
    // </editor-fold>
}
