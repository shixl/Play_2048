package com.example.sxl.utils;

import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by LJDY490 on 2017/3/23.
 */

public class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener {

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return super.onFling(e1, e2, velocityX, velocityY);
    }
}
