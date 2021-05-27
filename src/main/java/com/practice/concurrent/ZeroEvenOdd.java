package com.practice.concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import java.util.function.IntConsumer;

public class ZeroEvenOdd {

    private int n;
    private final AtomicInteger atomicInteger = new AtomicInteger(0);
    private volatile int index = 0;
    private final Map<String, Thread> map = new ConcurrentHashMap<>();

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    public void zero(IntConsumer printNumber) {
        map.put("zero", Thread.currentThread());
        for(int i = 1; i <= n; i++) {
            while (index != 0) {
                LockSupport.park();
            }
            printNumber.accept(0);
            atomicInteger.getAndIncrement();
            if(i % 2 == 0) {
                index = 2;
            } else {
                index = 1;
            }
            map.forEach((k, v) -> LockSupport.unpark(v));
        }
    }

    public void even(IntConsumer printNumber) {
        map.put("even", Thread.currentThread());
        for(int i = 0; i < n / 2; i++) {
            while (index != 2) {
                LockSupport.park();
            }
            printNumber.accept(atomicInteger.get());
            index = 0;
            LockSupport.unpark(Thread.currentThread());
        }
    }

    public void odd(IntConsumer printNumber) {
        map.put("odd", Thread.currentThread());
        for(int i = 0; i < (n+1) / 2; i++) {
            while (index != 1) {
                LockSupport.park();
            }
            printNumber.accept(atomicInteger.get());
            index = 0;
            LockSupport.unpark(Thread.currentThread());
        }
    }
}
