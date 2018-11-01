package com.tagore.challenge;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

    private double balance;
    private String accountNumber;
    private Lock lock;


    public BankAccount(double balance, String accountNumber) {
        this.balance = balance;
        this.accountNumber = accountNumber;
        lock = new ReentrantLock();
    }

//    public synchronized void deposit(double amount){
//        this.balance += amount;
//    }
//
//    public synchronized void withdraw(double amount){
//        this.balance -= amount;
//    }

    /*public void deposit(double amount){
        lock.lock();
        try {
            this.balance += amount;
        }finally {
            lock.unlock();
        }
    }

    public void withdraw(double amount){
        lock.lock();
        try {
            this.balance -= amount;
        }finally {
            lock.unlock();
        }
    }*/

    public void deposit(double amount){
        boolean status = false;
        try {
            if(lock.tryLock(1000, TimeUnit.MILLISECONDS)){
                try{
                    this.balance += amount;
                    status =true;
                } finally {
                    lock.unlock();
                }
            }
            else
            {
                System.out.println("Could not get the lock");
            }
        }catch (InterruptedException e) {

        }
        System.out.println("Deposit Status "+ status);
    }

    public void withdraw(double amount){
        boolean status = false;
        try {
            if(lock.tryLock(1000, TimeUnit.MILLISECONDS)){
                try{
                    this.balance -= amount;
                    status = true;
                } finally {
                    lock.unlock();
                }
            }
            else
            {
                System.out.println("Could not get the lock");
            }
        }catch (InterruptedException e) {

        }
        System.out.println("Withdrawl Status "+ status);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void printAccountNumber(){
        System.out.println("Account Number: "+ accountNumber);
    }
}
