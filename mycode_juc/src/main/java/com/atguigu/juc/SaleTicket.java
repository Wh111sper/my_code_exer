package com.atguigu.juc;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author MaoYuJian
 * @create 2020-11-26
 */


class Tickets {
    //定义票数
    private int number = 30;

    /*//synchronized 买票方法
    public synchronized void sale(){
        if (number>0){
            System.out.println(Thread.currentThread().getName()+"卖出："+number-- + "剩下"+ number);
        }
    }*/

//    lock实现  卖票的方法
    public void sale(){
        //创建可重入锁
        Lock lock = new ReentrantLock();

        //上锁
        lock.lock();

        //解锁

        try {
            //if (number>0){   //使用while 判断
            while (number>0){
                System.out.println(Thread.currentThread().getName()+"卖出："+number-- + "剩下"+ number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

//3个售票员  卖出  30张票
public class SaleTicket {
    public static void main(String[] args) {
        final Tickets tickets = new Tickets();

        /*new Thread(new Runnable() {
           public void run(){

           }
        },"AA").start();*/
        //从线程池中获取线程
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                8,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        for (int i = 0; i < 5; i++) {
            threadPool.execute(()->{
                tickets.sale();
            });
        }



    }
}
