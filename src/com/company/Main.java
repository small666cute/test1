package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        P p=new P();
        new Thread(new Producer(p)).start();
        new Thread(new Customer(p)).start();
    }
}
class P{
    private String name;
    private String sex;
    private boolean isfull=false;
    public synchronized void setP(String name,String sex){
        if(isfull==true){
            try{
                wait();
            }catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
        this.name=name;
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        this.sex=sex;
        isfull=true;
        notify();
    }
    public synchronized void getP(){
        if(isfull==false){
           try {
               wait();
           }catch (InterruptedException e) {
               System.out.println(e.getMessage());
           }
        }
        System.out.println(this.name+":"+this.sex);
        isfull=false;
        notify();
    }

}
class Producer implements Runnable{
   P p=null;
    Producer(P p) {
        this.p = p;
    }
    public void run(){
        int i=0;
        while(true){
            if(i==0)
                p.setP("name1","sex1");
            else
                p.setP("name2","sex2");
            i=(i+1)%2;
        }
    }
}
class Customer implements Runnable{
    P p=null;
    Customer(P p){
        this.p=p;
    }
    public void run(){
        while(true){
            p.getP();
        }
    }
}
