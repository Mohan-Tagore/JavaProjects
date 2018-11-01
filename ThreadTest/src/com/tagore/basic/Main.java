package com.tagore.basic;


import static com.tagore.basic.ThreadColor.*;

public class Main {

    public static void main(String[] args) {
        System.out.println(ANSI_PURPLE + "In the main thread");
        Thread anotherThread = new AnotherThread();
        anotherThread.setName("== AnotherThread ==");
        anotherThread.start();
        new Thread(){
            public void run() {
             System.out.println(ANSI_GREEN + "Hello from Anonymous class");
            }
        }.start();

        Thread myRunnableThread = new Thread(new MyRunnable(){
            @Override
            public void run() {
                System.out.println(ANSI_RED + "Hello from anonymous inner thread");
                try{
                    anotherThread.join(5000);
                    System.out.println(ANSI_RED + "Another Thread Terminated");
                }catch (InterruptedException e){
                    System.out.println(ANSI_RED + "I cudnt wait");
                }
            }
        });
        myRunnableThread.start();
        //anotherThread.interrupt();
        System.out.println(ANSI_PURPLE + "Hello again from Main Thread");
    }
}
