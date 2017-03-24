package com.example.sxl.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.example.sxl.utils.CustomGestureDetector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LJDY490 on 2017/3/22.
 */

public class Game2048Layout extends RelativeLayout {

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
     * 运动方向的枚举
     */
    private enum ACTION {
        LEFT, RIGHT, UP, DOWN
    }

    public interface OnGame2048Listener {

        void onChangeScore(int score);

        void onGameOver();
    }

    private OnGame2048Listener mOnGame2048Listemer;

    private void setOnGame2048Listener(OnGame2048Listener onGame2048Listener) {
        this.mOnGame2048Listemer = onGame2048Listener;
    }

    public Game2048Layout(Context context) {
        this(context, null);
    }

    public Game2048Layout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Game2048Layout(Context context, AttributeSet attrs, int defStyleAtt) {
        super(context, attrs, defStyleAtt);

        mMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mMargin, getResources().getDisplayMetrics());
        mPadding = min(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        mGestureDetector = new GestureDetector(context, new CustomGestureDetector());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 根据用户运动,整体进行移动跟合并
     */
    private void action(ACTION action) {
        //行|列
        for (int i = 0; i < mColumn; i++) {
            List<Game2048Item> row = new ArrayList<>();
            // 行|列
            //记录不为0的数字
            for (int j = 0; j < mColumn; j++) {
                //得到下标
                int index = getIndexByAction(action, i, j);
                Game2048Item item = mGame2048Items[index];
                //记录不为0的数字
                if (item.getNumber() != 0) {
                    row.add(item);
                }
            }

            //判断是否移动
            for (int j = 0; j < mColumn && j < row.size(); j++) {
                int index = getIndexByAction(action, i, j);
                Game2048Item item = mGame2048Items[index];

                if (item.getNumber() != row.get(j).getNumber()) {
                    isMoveHappen = true;
                }
            }

            //合并相同的
            mergeItem(row);

            //设置合并后的值
            for (int j = 0; j < mColumn; j++) {
                int index = getIndexByAction(action, i, j);

                if (row.size() > j) {
                    mGame2048Items[index].setNumber(row.get(j).getNumber());
                } else {
                    mGame2048Items[index].setNumber(0);
                }
            }

            //生成数字
            generateNum();

        }
    }

    /**
     * 产生一个数字
     */
    private void generateNum() {

    }

    /**
     * 是否填满数字
     *
     * @return
     */
    private boolean isFull()
    {
        // 检测是否所有位置都有数字
        for (int i = 0; i < mGame2048Items.length; i++)
        {
            if (mGame2048Items[i].getNumber() == 0)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * 检测当前所有的位置都有数字，且相邻的没有相同的数字
     *
     * @return
     */
    private boolean checkOver(){
        //检测是否所有位置都有数字
        if(!isFull()){
            return false;
        }

        for(int i = 0; i < mColumn ; i++){
            for(int j = 0 ; j < mColumn; j++){
                int index = i * mColumn + j;

                //当前的item
                Game2048Item item = mGame2048Items[index];

                // 右边
                if ((index + 1) % mColumn != 0)
                {
                    Log.e("TAG", "RIGHT");
                    // 右边的Item
                    Game2048Item itemRight = mGame2048Items[index + 1];
                    if (item.getNumber() == itemRight.getNumber())
                        return false;
                }
                // 下边
                if ((index + mColumn) < mColumn * mColumn)
                {
                    Log.e("TAG", "DOWN");
                    Game2048Item itemBottom = mGame2048Items[index + mColumn];
                    if (item.getNumber() == itemBottom.getNumber())
                        return false;
                }
                // 左边
                if (index % mColumn != 0)
                {
                    Log.e("TAG", "LEFT");
                    Game2048Item itemLeft = mGame2048Items[index - 1];
                    if (itemLeft.getNumber() == item.getNumber())
                        return false;
                }
                // 上边
                if (index + 1 > mColumn)
                {
                    Log.e("TAG", "UP");
                    Game2048Item itemTop = mGame2048Items[index - mColumn];
                    if (item.getNumber() == itemTop.getNumber())
                        return false;
                }
            }
        }

        return true;
    }

    /**
     * 合并相同的item
     *
     * @param row
     */
    private void mergeItem(List<Game2048Item> row) {
        if (row.size() < 2) {
            return;
        }

        for (int j = 0; j < row.size() - 1; j++) {
            Game2048Item item1 = row.get(j);
            Game2048Item item2 = row.get(j + 1);
            if (item1.getNumber() == item2.getNumber()) {
                isMergeHappen = true;

                int val = item1.getNumber() + item2.getNumber();
                item1.setNumber(val);

                //加分
                mScore += val;
                if (mOnGame2048Listemer != null) {
                    mOnGame2048Listemer.onChangeScore(mScore);
                }

                //向前移动
                for (int k = j + 1; k < row.size() - 1; k++) {
                    row.get(k).setNumber(row.get(k + 1).getNumber());
                }

                row.get(row.size() - 1).setNumber(0);
                return;
            }
        }
    }

    /**
     * 根据Action和i,j得到下标
     *
     * @param action
     * @param i
     * @param j
     * @return
     */
    private int getIndexByAction(ACTION action, int i, int j) {
        int index = -1;
        switch (action) {
            case LEFT:
                index = i * mColumn + j;
                break;
            case RIGHT:
                index = i * mColumn + mColumn - j - 1;
                break;
            case UP:
                index = i + j * mColumn;
                break;
            case DOWN:
                index = i + (mColumn - 1 - j) * mColumn;
                break;
        }
        return index;
    }

    /**
     * 得到多值的最小值
     *
     * @param params
     * @return
     */
    private int min(int... params) {
        int min = params[0];

        for (int param : params) {
            if (min > param) {
                min = param;
            }
        }

        return min;
    }
}
