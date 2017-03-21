package com.example.sxl.view;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by LJDY490 on 2017/3/21.
 */

public class Game2048Item extends View {

    /**
     * view上的数字
     */
    private int mNumber;
    private String mNumberVal;
    private Paint mPaint;

    /**
     * 绘制文字的区域
     */
    private Rect mRect;

    public Game2048Item(Context context) {
        this(context,null);
    }

    public Game2048Item(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Game2048Item(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
    }

    public void setNumber(int number){
        mNumber = number;
        mNumberVal = mNumber + "";
        mPaint.setTextSize(30f);
        mRect = new Rect();
        mPaint.getTextBounds(mNumberVal,0,mNumberVal.length(),mRect);
        invalidate();
    }
}
