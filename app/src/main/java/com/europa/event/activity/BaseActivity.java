package com.europa.event.activity;

import android.os.Bundle;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.europa.event.ViewLog;
import com.europa.event.view.ViewBase;


public class BaseActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName()+"---%s";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewLog.d(TAG,"");
    }

    @Override
    protected void onStart() {
        super.onStart();
        ViewLog.d(TAG,"");
    }

    @Override
    protected void onResume() {
        super.onResume();
        ViewLog.d(TAG,"");
    }

    @Override
    protected void onPause() {
        super.onPause();
        ViewLog.d(TAG,"");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ViewLog.d(TAG,"");
    }

    @Override
    protected void onStop() {
        super.onStop();
        ViewLog.d(TAG,"");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewLog.d(TAG,"");
    }

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
