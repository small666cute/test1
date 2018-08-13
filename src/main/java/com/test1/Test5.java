package com.test1;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.*;

public class Test5 {
    public static void main(String args[]) throws Exception {
        String str = "akdjA";
        byte buf1[] = str.getBytes();
        ByteArrayInputStream in = new ByteArrayInputStream(buf1);
        ByteOutputStream ou = new ByteOutputStream();
        new Test5().byteCom(in, ou);
        byte buf2[] = ou.getBytes();
        System.out.println(new String(buf2));
    }

    public void byteCom(InputStream in, OutputStream ou) {
        int ch1 = 0;
        try {
            while ((ch1 = in.read()) != -1) {
                //int ch2 = (int) Character.toUpperCase((char) ch1);
                ou.write(ch1);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
