package com.atguigu.interview;

import java.util.concurrent.TimeUnit;

/**
 * @author MaoYuJian
 * @create 2020-11-27
 */
class MyDate{
    volatile int number = 10;

    public void changeData(){
        this.number = 60;
    }
}

public class VolatileDemo {
    public static void main(String[] args) {
        MyDate myDate = new MyDate();

        /*try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        new Thread(() ->{
            System.out.println(Thread.currentThread().getName()+"   come in " +myDate.number);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            myDate.changeData();
            System.out.println(Thread.currentThread().getName()+"   updated  " +myDate.number);


        },"AAA").start();


        while (myDate.number == 10){
//            System.out.println("DeadLoop");
        }

        System.out.println(Thread.currentThread().getName()+"   mission is over , main get number ="+myDate.number);
    }
}
