package com.tagore.challenge;

public class Main {

    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(1000.00, "12345-678");
        Customer1 customer1 = new Customer1(bankAccount);
        Customer2 customer2 = new Customer2(bankAccount);

        Thread cust1 = new Thread(customer1);
        Thread cust2 = new Thread(customer2);


        /*cust1.start();
        cust2.start();
        */
        (new Thread(){
            @Override
            public void run() {
                bankAccount.deposit(300.00);
              //  System.out.println(300.00 + " amount deposited by customer INAT1");
                bankAccount.withdraw(50.00);
                //System.out.println(50.00 + " amount withdrawn by customer INTA1");
            }
        }).start();

        (new Thread(){
            @Override
            public void run() {
                bankAccount.deposit(203.75);
             //   System.out.println(203.75 + " amount deposited by customer INAT2");
                bankAccount.withdraw(100.00);
               // System.out.println(100.00 + " amount withdrawn by customer INAT2");
            }
        }).start();
    }



}

class Customer1 implements Runnable{

    private BankAccount bankAccount;

    public Customer1(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public void run() {
        try {

            bankAccount.deposit(300.00);
            System.out.println(300.00 + " amount deposited by customer 1");
            bankAccount.withdraw(50);
            System.out.println(50 + " amount withdrawn by customer 1");
            Thread.sleep(2000);
        }catch (InterruptedException e){

        }

    }
}

class Customer2 implements Runnable{

    private BankAccount bankAccount;

    public Customer2(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public void run() {
        try{
            bankAccount.deposit(203.75);
            System.out.println(203.75 + " amount deposited by customer 2");
            bankAccount.withdraw(100.00);
            System.out.println(100 + " amount withdrawn by customer 2");
            Thread.sleep(2000);
        }catch (InterruptedException e){

        }

    }
}