package com.europa.event.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.europa.event.R;
import com.europa.event.ViewLog;

import static com.europa.event.view.ViewBase.getEventName;

public class ParentView extends ViewGroup {
    private String TAG = getClass().getSimpleName();
    boolean downIntercepted, moveIntercepted, upIntercepted, downTouched, moveTouched, upTouched = false;

    public ParentView(Context context) {
        super(context);
    }

    public ParentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.viewgroup);
        downIntercepted = a.getBoolean(R.styleable.viewgroup_down_intercepted, false);
        moveIntercepted = a.getBoolean(R.styleable.viewgroup_move_intercepted, false);
        upIntercepted = a.getBoolean(R.styleable.viewgroup_up_intercepted, false);
        downTouched = a.getBoolean(R.styleable.viewgroup_down_touched, false);
        moveTouched = a.getBoolean(R.styleable.viewgroup_move_touched, false);
        upTouched = a.getBoolean(R.styleable.viewgroup_up_touched, false);
        String tag = getTag().toString();
        TAG = tag.isEmpty() ? TAG : tag;
        TAG += "---%s";
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View child = getChildAt(0);
        LayoutParams st = child.getLayoutParams();
        child.layout(0, 0, st.width, st.height);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        ViewLog.d(TAG, getEventName(ev));
        return super.dispatchTouchEvent(ev);
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

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        ViewLog.d(TAG, ViewBase.getEventName(ev));
        if (downIntercepted || moveIntercepted || upIntercepted) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    return downIntercepted ? downIntercepted : super.onInterceptTouchEvent(ev);
                case MotionEvent.ACTION_MOVE:
                    return moveIntercepted ? moveIntercepted : super.onInterceptTouchEvent(ev);
                case MotionEvent.ACTION_UP:
                    return upIntercepted ? upIntercepted : super.onInterceptTouchEvent(ev);
            }
        }
        return super.onInterceptTouchEvent(ev);
    }
}
