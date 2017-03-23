package com.example.sxl.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.ViewGroup;

import com.example.sxl.utils.CustomGestureDetector;

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

    /**
     *运动方向的枚举
     */
    private enum ACTION{
        LEFT,RIGHT,UP,DOWN
    }

    public interface OnGame2048Listener{

        void onChangeScore(int score);

        void onGameOver();
    }

    private OnGame2048Listener mOnGame2048Listemer;

    private void setOnGame2048Listener(OnGame2048Listener onGame2048Listener){
        this.mOnGame2048Listemer = onGame2048Listener;
    }

    public Game2048Layout(Context context) {
        this(context,null);
    }

    public Game2048Layout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Game2048Layout(Context context, AttributeSet attrs, int defStyleAtt) {
        super(context, attrs, defStyleAtt);

        mMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,mMargin,getResources().getDisplayMetrics());
        mPadding = min(getPaddingLeft(),getPaddingTop(),getPaddingRight(),getPaddingBottom());
        mGestureDetector = new GestureDetector(context,new CustomGestureDetector());
    }

    /**
     * 根据用户运动,整体进行移动跟合并
     */
    private void action(ACTION action){

    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    /**
     * 得到多值的最小值
     * @param params
     * @return
     */
    private int min(int... params){
        int min = params[0];

        for(int param: params){
            if(min > param){
                min = param;
            }
        }

        return min;
    }
}
