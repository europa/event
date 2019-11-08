package com.europa.event;

import org.junit.Test;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws CannotCompileException, NotFoundException, IOException {

        ClassPool pool = ClassPool.getDefault();
        //创建类
        CtClass ss = pool.makeClass("com.europa.event.test");

        //创建属性
        CtField f1 = CtField.make("private int empno;", ss);
        CtField f2 = CtField.make("private String ename;", ss);
        ss.addField(f1);
        ss.addField(f2);

        //创建方法
        CtMethod m1 = CtMethod.make("public int getEmpno(){return empno;}", ss);
        CtMethod m2 = CtMethod.make("public void setEmpno(int empno){this.empno=empno;}", ss);
        ss.addMethod(m1);
        ss.addMethod(m2);

        //添加构造器
        CtConstructor constructor = new CtConstructor(new CtClass[]{CtClass.intType,pool.get("java.lang.String")}, ss);
        constructor.setBody("{this.empno=empno; this.ename=ename;}");
        ss.addConstructor(constructor);

//        ss.writeFile("F:\\eclipse-test\\Javassist_test"); //将上面构造好的类写入到c:/myjava中
        ss.writeFile(); //将上面构造好的类写入到c:/myjava中
        System.out.println("生成类，成功！");



        assertEquals(4, 2 + 2);
    }
}