package com.europa.event.activity;

import android.os.Bundle;
import android.view.MotionEvent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import com.europa.event.R;
import com.europa.event.ViewLog;

public class InterceptAndTouchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interceptandtouch);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
//            ((TextView)findViewById(R.id.tip)).setText(R.string.large_text);
        }
        return super.onTouchEvent(event);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean dispatchTouchEvent = super.dispatchTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_UP) {
            String onceLog = ViewLog.ONCE_LOGS;
            if (onceLog.contains("onInterceptTouchEvent: blue---ACTION_DOWN\n" +
                    ";downIntercepted:true")) {
                new AlertDialog.Builder(this).setMessage(getString(R.string.highdown_lowdown_tip) + onceLog).show();
            } else if (onceLog.contains("onTouchEvent: green---ACTION_DOWN;\n" +
                    "downTouched:true;")) {
                new AlertDialog.Builder(this).setMessage(getString(R.string.highmove_lowdown_tip) + onceLog).show();
            } else {
                new AlertDialog.Builder(this).setMessage(getString(R.string.lowdown_highdown_tip) + onceLog).show();
            }
            ViewLog.clearOnceLog();
        }
        return dispatchTouchEvent;
    }

}
