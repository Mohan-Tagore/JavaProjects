package com.tagore.deadlock;

public class SayHello {

    public static void main(String[] args) {
     final PolitePerson mohan = new PolitePerson("Mohan");
     final PolitePerson tagore = new PolitePerson("Tagore");

        (new Thread(new Runnable() {
            @Override
            public void run() {
                mohan.sayHello(tagore);
            }
        })).start();

        (new Thread(new Runnable() {
            @Override
            public void run() {
                tagore.sayHello(mohan);
            }
        })).start();

    }

    static class PolitePerson{
        private final String name;

        PolitePerson(String name){
            this.name =name;
        }

        public String getName() {
            return name;
        }

        public synchronized void sayHello(PolitePerson person){
            System.out.format("%s: %s" + " has said Hello to me \n",this.name, person.getName());
            person.sayHelloBack(this);
        }

        public synchronized void sayHelloBack(PolitePerson person){
            System.out.format("%s: %s" + " has said Hello back to me \n",this.name, person.getName());
        }
    }
}
