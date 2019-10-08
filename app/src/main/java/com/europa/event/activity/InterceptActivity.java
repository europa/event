package com.europa.event.activity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import com.europa.event.R;
import com.europa.event.ViewLog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class InterceptActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intercept);
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
        boolean superDispatch = super.dispatchTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_UP) {
            String onceLog = ViewLog.ONCE_LOGS;
            if (onceLog.contains("downIntercepted:true")) {
                new AlertDialog.Builder(this).setMessage(getString(R.string.intercept_down) + onceLog).show();
            } else {
                new AlertDialog.Builder(this).setMessage(getString(R.string.intercept_move) + onceLog).show();
            }
            ViewLog.clearOnceLog();
        }
        return superDispatch;
    }

}
