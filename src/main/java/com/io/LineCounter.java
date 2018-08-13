package com.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
//练习I/O中的封装类
//对文本文件的行数进行计数
public class LineCounter {
    public static void main(String[] args) {
        LineNumberReader reader = null;
        if(args.length<1){
            System.out.println("need a file name");
            System.exit(0);
        }
        try{
            reader = new LineNumberReader(new FileReader(args[0]));
            //读文件直到读完为止，但是我们不需要这个内容，所以就不管这个内容
            while (reader.readLine()!=null){

            }
            System.out.println("这个文件的行数是:"+reader.getLineNumber()+1);
        }catch (FileNotFoundException fe){
            System.out.println(fe.getMessage());
        }catch (IOException e){
            System.out.println("error reading file");
        }finally {
            try{
                reader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
