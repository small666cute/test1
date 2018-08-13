package com.test1;

import java.io.*;

public class Test4 {
    public static void main(String args[]) {
        try {
            Sender s = new Sender();
            Receiver r = new Receiver();
            PipedOutputStream ou = s.getOutputstream();
            PipedInputStream in = r.getInputstream();
            ou.connect(in);
            s.start();
            r.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Sender extends Thread {
    private PipedOutputStream ou = new PipedOutputStream();

    public PipedOutputStream getOutputstream() {
        return ou;
    }

    public void run() {
        String str = "12345";
        try {
            ou.write(str.getBytes());
            ou.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

class Receiver extends Thread {
    private PipedInputStream in = new PipedInputStream();

    public PipedInputStream getInputstream() {
        return in;
    }

    public void run() {
        byte buf[] = new byte[1024];
        int i = 0;
        try {
            i = in.read(buf);
            System.out.println("shou dao le:" + new String(buf, 0, i));
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
