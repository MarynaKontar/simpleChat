package com.app.MultiTheading;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by User on 03.07.2017.
 */
public class CounterTest {
    private static class Counter{
        private int i;
        private Object mu = new Object();

        private AtomicInteger c = new AtomicInteger();// !!!!!ОБЕСПЕЧИВАЕТ ПОТОКОБЕЗОПАСНОСТЬ
        private int getAndIncrement(){

//            // критическая секция (окружена synchronized) = монитор. Пока эту секцию не выполнят, заново нельзя зайти другому thread
//            synchronized (this) {//блок по экземпляру текущего класса synchronize. Можно synchronized (this) или synchronized (mu)
////            int tmp = i;
////            i++;
////            return tmp;
//            return i++;
//        }

            return c.getAndIncrement();

        }
        private int get(){
            return i;
        }
    }

    public static void main(String[] args) {
        Counter c = new Counter();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread() + ":" + c.getAndIncrement());
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        };
        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();
    }
}
