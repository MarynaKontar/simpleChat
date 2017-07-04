package com.app.MultiTheading;

/**
 * Created by User on 03.07.2017.
 */
public class Threads {


    public static void main(String[] args) throws InterruptedException {
        System.out.println(Runtime.getRuntime().availableProcessors());

//        System.out.println(Thread.currentThread());
//        Thread thread = new Thread(() -> System.out.println(Thread.currentThread() + "Hello thread"));
//        Thread thread = new Thread(){
//            @Override public void run(){
//                System.out.println(Thread.currentThread() + "Hello thread");
//            }
//        };



        Runnable rn = new Runnable() {
            @Override
            public void run() {
                Thread t = Thread.currentThread();
                System.out.println(t.getId() + " " + t + "Hello thread");
                try {
                    Thread.sleep(3000);//поток спит, но системній схедлер все равно на него тратит время (проверяет спит ли); а если запарковать, то поток вообше вілетает из пула и схедлер его не видит
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            Thread t = new Thread(rn, "CustomThread_" + i);
            t.start();
            t.setDaemon(true);//метим поток как не важный и его можно прервать в любой момент даже, если он не выполнился
            threads[i] = t;
        }

        threads[4].setPriority(10);//лучший приоритет (max приоритет)

//        for (Thread thread : threads) {
//            thread.join();
//        }

//        thread.start();
//        thread.join(); //тормозит текуший поток (основной), пока не выполнится нужный поток

//        Thread[Thread-0,5,main]Hello thread
    }
}
