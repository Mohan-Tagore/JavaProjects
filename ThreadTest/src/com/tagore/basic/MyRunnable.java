package com.tagore.basic;


import static com.tagore.basic.ThreadColor.ANSI_RED;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(ANSI_RED + "Hello from my Runnable");
    }
}
