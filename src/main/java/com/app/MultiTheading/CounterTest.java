package com.app.MultiTheading;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by User on 03.07.2017.
 */
public class CounterTest {

    private static class Counter {
        private int i;
        private Object mu = new Object(); //mutex - отдельный объект, который используется только для синхронизации

        //в java.util.concurrent.atomic есть Atomic primitives (AtomicInteger, AtomicLong,...), команды которых
        // (getAndIncrement(), например) гарантируют атомарность
        private AtomicInteger c = new AtomicInteger();// !!!!!ОБЕСПЕЧИВАЕТ ПОТОКОБЕЗОПАСНОСТЬ использовать вместо і++
        private AtomicLong cLong = new AtomicLong();// !!!!!ОБЕСПЕЧИВАЕТ ПОТОКОБЕЗОПАСНОСТЬ использовать сместо і++


        //
        private long getAndIncrement() {
//            // критическая секция (окружена synchronized) = монитор(в эту секцию,
//            //в один момент времени, захватывая монитор может зайти только один поток (thread)).
//            //Пока эту секцию не выполнят, заново нельзя зайти другому thread
//            synchronized (this) {//synchronized блок по экземпляру текущего класса
//            //(т.е. делаем потокобезопасным по объекту и монитору (в скобках - this=экземпляр класса Counter)).
//            //Можно synchronized (this) или synchronized (mu)- mutex. Ничем эти два способа не отличается.
            //synchronized (this) равноценно синхронизации по методу. Тю ею когда пишем synchronized метод,
            // это то же самое,что synchronized (this).
            // Т.е. когда поток заходит в synchronized метод, он видит и др. synchronized методы этого класса.
            // Свойство, когда один видет результаты другого справедливо только
//            когда они на ОДНОМ мониторе.
            // Главное, чтобы синхронизация была по одному и тому же монитору.

//            long tmp = i;
//            i++;
//            return tmp;
            return i++;
//        }
//            return c.getAndIncrement();
        }

        private long get() {
            synchronized (this) {
//            synchronized (mu){
                return i;
//                return c.get();
            }
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
