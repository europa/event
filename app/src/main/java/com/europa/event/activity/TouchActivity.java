package com.europa.event.activity;

import android.os.Bundle;
import android.view.MotionEvent;

import androidx.appcompat.widget.Toolbar;

import com.europa.event.R;

public class TouchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP){
//            ((TextView)findViewById(R.id.tip)).setText(R.string.large_text);
        }
        return super.onTouchEvent(event);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP){
//            ((TextView)findViewById(R.id.tip)).setText(R.string.large_text);
        }
        return super.dispatchTouchEvent(event);
    }

}
