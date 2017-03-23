package com.example.sxl.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.ViewGroup;

/**
 * Created by LJDY490 on 2017/3/22.
 */

public class Game2048Layout extends ViewGroup {

    /**
     * 设置item的数量n*n 默认为4
     */
    private int mColumn = 4;

    /**
     * 存放所有的item
     */
    private Game2048Item[] mGame2048Items;

    /**
     * 甚至item的边距
     */
    private int mMargin = 10;

    /**
     * 面板的padding
     */
    private int mPadding;

    /**
     * 用于处理手势的处理
     */
    private GestureDetector mGestureDetector;

    // 用于确认是否需要生成一个新的值
    private boolean isMergeHappen = true;
    private boolean isMoveHappen = true;

    /**
     * 记录分数
     */
    private int mScore;

    public Game2048Layout(Context context) {
        super(context);
    }

    public Game2048Layout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Game2048Layout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }
}
