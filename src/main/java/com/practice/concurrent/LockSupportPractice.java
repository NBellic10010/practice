package com.practice.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

public class LockSupportPractice {
    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(4, 20, 60L, TimeUnit.SECONDS, new SynchronousQueue<>(), (ThreadFactory) Thread::new);
        for(int i = 0; i < 5; i++) {
            es.execute(new Runnable() {
                private int count = 0;
                @Override
                public void run() {
                    long start = System.currentTimeMillis();
                    long end = 0;

                    while (end <= start + 1000) {
                        ++count;
                        end = System.currentTimeMillis();
                    }

                    System.out.println("after 1 secound, count = " + count);

                    //LockSupport.park();
                    System.out.println("thread over. " + (Thread.currentThread().isInterrupted() ? "Thread interrupted" : "Thread not interrupted"));
                }
            });
        }
    }
}
