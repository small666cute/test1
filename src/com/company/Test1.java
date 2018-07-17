package com.company;

public class Test1 {
    public static void main(String args[]) {
        Stopthread t = new Stopthread();
        t.start();
        for (int i = 0; i < 5; i++) {
            System.out.println("yun xing zhong-" + Thread.currentThread().getName());
            if (i == 2)
                t.stopMe();
            try{
                Thread.sleep(2000);
            }catch (Exception e){
                System.out.println(e);
            }

        }
    }
}

class Stopthread extends Thread {
    private boolean isstop = true;
    int i=0;
    public void stopMe() {
        isstop = false;
    }

    public void run() {
        while (isstop) {
            System.out.println("yun xing zhong-" + Thread.currentThread().getName());
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
}