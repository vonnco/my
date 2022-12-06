package com.vonco.juc.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ke feng
 * @title: Test06
 * @projectName my
 * @description: TODO
 * @date 2022/8/16 10:50
 */
public class Test06 {
    public static void main(String[] args) {
        Test06 test06 = new Test06();
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    test06.method01();
                }
            }).start();
        }
    }

    private void method01(){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            method02(list);
            method03(list);
        }
    }

    private void method02(List<String> list){
        list.add("1");
    }

    private void method03(List<String> list){
        list.remove(0);
    }
}
