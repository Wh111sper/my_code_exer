package com.atguigu;

import java.util.concurrent.TimeUnit;

/**
 * @author MaoYuJian
 * @create 2020-11-25
 */
public class Demo1 {
    public static void main(String[] args) {
        final Object objectA = new Object();
        final Object objectB = new Object();
        new Thread(() -> {
            synchronized (objectA){
                System.out.println("线程A拿到objectA,想要控制objectB");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (objectB){
                    System.out.println("A线程拿到objectB");
                }
            }
        },"A").start();

        new Thread(() -> {
            synchronized (objectB){
                System.out.println("线程B拿到objectB,想要控制objectA");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (objectA){
                    System.out.println("B线程拿到objectA");
                }
            }
        },"B").start();
    }
}


