package com.atguigu;



import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author MaoYuJian
 * @create 2020-11-25
 */
public class DeadLock {
    public static void main(String[] args) {

    }
}

class SS{
    Object o1 = new Object();
    Object o2 = new Object();

    Lock lock = new ReentrantLock();

    public void t1(){
        lock.lock();
    }
}

