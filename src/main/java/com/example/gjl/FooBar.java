package com.example.gjl;

class FooBar {
    private int n;
    private static Object lock=new Object();
    private int tmp=1;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                if(tmp!=1) lock.wait();
                tmp=2;
                printFoo.run();
                lock.notifyAll();
            }
        }
    }
    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                if(tmp!=2) lock.wait();
                tmp=1;
                printBar.run();
                lock.notify();
            }
        }
    }
}