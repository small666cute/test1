package com.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FileLength {
    public static void main(String[] args) {
        int count = 0;
        InputStream streamReader = null;//
        if (args.length < 1) {
            System.out.println("usage:java FileLength<filename>");
            System.exit(0);
        }
        try {
            streamReader = new FileInputStream(args[0]);
            while (streamReader.read() == -1) {
                count++;
            }
            System.out.println(args[0] + "length=" + count);
            streamReader.close();
        } catch (FileNotFoundException fe) {
            System.out.println("file"+args[0]+"no found");
            System.exit(0);
        }catch (IOException ie){
            System.out.println("Io ERROR");
        }finally {
            try {
                streamReader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
