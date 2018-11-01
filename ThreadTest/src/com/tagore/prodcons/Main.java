package com.tagore.prodcons;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import static com.tagore.prodcons.Main.EOF;
import static com.tagore.prodcons.ThreadColor.*;


public class Main {

    public static final String EOF = "EOF";

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        ReentrantLock reentrantLock = new ReentrantLock();
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        MyProducer myProducer = new MyProducer(list, ANSI_GREEN, reentrantLock);
        MyConsumer consumer1 = new MyConsumer(list, ANSI_BLUE, reentrantLock);
        MyConsumer consumer2 = new MyConsumer(list, ANSI_CYAN, reentrantLock);

        executorService.execute(myProducer);
        executorService.execute(consumer1);
        executorService.execute(consumer2);

        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("Future code block");
                return "Future recalled";
            }
        });

        try{
            System.out.println(future.get());
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}

class MyProducer implements Runnable{
    private List<String> buffer;
    private String color;
    private ReentrantLock reentrantLock;

    public MyProducer(List<String> buffer, String color, ReentrantLock reentrantLock) {
        this.buffer = buffer;
        this.color = color;
        this.reentrantLock = reentrantLock;
    }

    @Override
    public void run() {
        String [] messages = {"1", "2", "3", "4", "5"};
        Random random = new Random();
        for (String message : messages){
            try {
                System.out.println(color +"Adding..." + message);
                reentrantLock.lock();
                try{
                  buffer.add(message);
                }finally {
                   reentrantLock.unlock();
               }
                Thread.sleep(random.nextInt(2000));
            }catch (InterruptedException e){
                System.out.println("Producer was interrupted");
            }
        }
        reentrantLock.lock();
        try{
            buffer.add(EOF);
        }finally {
           reentrantLock.unlock();
        }
    }
}

class MyConsumer implements Runnable{
    private List<String> buffer;
    private String color;
    private ReentrantLock reentrantLock;

    public MyConsumer(List<String> buffer, String color, ReentrantLock reentrantLock) {
        this.buffer = buffer;
        this.color = color;
        this.reentrantLock = reentrantLock;
    }

    @Override
    public void run() {
        int counter =0;
      Random random = new Random();
        while (true){
            if(reentrantLock.tryLock()){
                try {
                    if(buffer.isEmpty()) {
                        //         reentrantLock.unlock();
                        continue;
                    }
                    System.out.println("Counter Value: "+ counter);
                    counter=0;
                    if(!buffer.get(0).equals(EOF)) {
                        System.out.println(color + buffer.remove(0) + " removed");
                    }
                    else{
                        System.out.println(color + "Reached end of the file");
                        //          reentrantLock.unlock();
                        break;
                    }
                }finally {
                    reentrantLock.unlock();
                }
            }else{
                counter++;
            }

        }
    }
}