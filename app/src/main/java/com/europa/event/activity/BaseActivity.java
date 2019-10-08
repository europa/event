package com.europa.event.activity;

import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

import com.europa.event.ViewLog;
import com.europa.event.view.ViewBase;


public class BaseActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName()+"---%s";
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        ViewLog.d(TAG, ViewBase.getEventName(ev));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        ViewLog.d(TAG, ViewBase.getEventName(event));
        return super.onTouchEvent(event);
    }
}
