package com.europa.event.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.europa.event.R;
import com.europa.event.ViewLog;


public class ViewBase extends View {
    private String TAG = getClass().getSimpleName();
    boolean downTouched, moveTouched, upTouched = false;

    public ViewBase(Context context) {
        super(context);
    }

    public ViewBase(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.viewgroup);
        downTouched = a.getBoolean(R.styleable.viewgroup_down_touched, false);
        moveTouched = a.getBoolean(R.styleable.viewgroup_move_touched, false);
        upTouched = a.getBoolean(R.styleable.viewgroup_up_touched, false);
        String tag = getTag().toString();
        TAG = tag.isEmpty() ? TAG : tag;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        ViewLog.d(TAG, getEventName(event));
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        ViewLog.d(TAG, getEventName(event));
        if (downTouched || moveTouched || upTouched) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    return downTouched ? downTouched : super.onTouchEvent(event);
                case MotionEvent.ACTION_MOVE:
                    return moveTouched ? moveTouched : super.onTouchEvent(event);
                case MotionEvent.ACTION_UP:
                    return upTouched ? upTouched : super.onTouchEvent(event);
            }
        }
        return super.onTouchEvent(event);
    }

    public static String getEventName(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN)
            return "ACTION_DOWN";
        if (action == MotionEvent.ACTION_MOVE)
            return "ACTION_MOVE";
        if (action == MotionEvent.ACTION_UP)
            return "ACTION_UP";
        return "Other: " + action;
    }
}
