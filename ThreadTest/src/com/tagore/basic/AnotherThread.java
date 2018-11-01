package com.tagore.basic;

import static com.tagore.basic.ThreadColor.ANSI_BLUE;

public class AnotherThread extends Thread{

    @Override
    public void run() {
        System.out.println(ANSI_BLUE + "Hello from " + currentThread().getName());
        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){
            System.out.println(ANSI_BLUE + "Interrupted during Sleep");
            return;
        }
        System.out.println(ANSI_BLUE + "Hello from 3 secs over");
    }
}
