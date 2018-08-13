package com.test1;

import java.io.*;

public class Test2 {
    public static void main(String args[]) {
        File f = new File("d:\\test.java");
        OutputStream ou = null;
        try {
            ou = new FileOutputStream(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = "123";
        try {
            ou.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*try {
            ou.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        InputStream in = null;
        try {
            in = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte arr[] = new byte[1024];
        int i = 0;
        try {
            i = in.read(arr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(new String(arr, 0, i));
    }
}
