package com.europa.event.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.europa.event.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

public class ScrollingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ClassPool pool = ClassPool.getDefault();
        try {
            CtClass ss = pool.makeClass("com.europa.event.test");
            CtField f1 = CtField.make("private int empno;", ss);
            CtField f2 = CtField.make("private String ename;", ss);
            ss.addField(f1);
            ss.addField(f2);
            CtMethod m1 = CtMethod.make("public int getEmpno(){return empno;}", ss);
            CtMethod m2 = CtMethod.make("public void setEmpno(int empno){this.empno=empno;}", ss);
            ss.addMethod(m1);
            ss.addMethod(m2);
            CtConstructor constructor = new CtConstructor(new CtClass[]{CtClass.intType, pool.get("java.lang.String")}, ss);
            constructor.setBody("{this.empno=empno; this.ename=ename;}");
            ss.addConstructor(constructor);
            ss.writeFile();
            System.out.println("生成类，成功！");
            System.out.println("success");
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }


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

        findViewById(R.id.tool_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(ToolActivity.class);
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
