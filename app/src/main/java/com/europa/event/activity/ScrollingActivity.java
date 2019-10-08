package com.europa.event.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.europa.event.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class ScrollingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.normal_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(NormalActivity.class);
            }
        });
        findViewById(R.id.intercept_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(InterceptActivity.class);
            }
        });

        findViewById(R.id.touch_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(TouchActivity.class);
            }
        });

        findViewById(R.id.interceptandtouch_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(InterceptAndTouchActivity.class);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void toActivity(Class<?> to) {
        Intent intent = new Intent();
        intent.setClass(this, to);
        startActivity(intent);
    }


}
