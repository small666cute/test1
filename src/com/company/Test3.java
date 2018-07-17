package com.company;

import java.io.*;

public class Test3 {
    public static void main(String args[]) {
        File f = new File("d://test.txt");
        Writer w = null;
        try {
            w = new FileWriter(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = "1234";
        try {
            w.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*try {
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        Reader r = null;
        try {
            r = new FileReader(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        char ch[] = new char[1024];
        int i = 0;
        try {
            i=r.read(ch);
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            r.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(new String(ch,0,i));

    }
}
