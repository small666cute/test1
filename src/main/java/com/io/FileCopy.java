package com.io;

import java.io.*;

public class FileCopy {
    public static void main(String[] args) {
        int numberRead = 0;
        InputStream readerStream = null;
        OutputStream writerStream = null;
        byte buffer[]=new byte[512];
        if(args.length<2){
            System.out.println("需要两个文件，然后把一个复制到另一个里面去");
            System.exit(0);
        }
        try{
            readerStream = new FileInputStream(args[0]);
        }catch (FileNotFoundException fe){
            System.out.println(args[0]+"not found");
            System.exit(0);
        }
        try{
            writerStream = new FileOutputStream(args[1]);
        }catch (FileNotFoundException fe){
            System.out.println(args[1]+"not found");
            System.exit(0);
        }
        //如果从readStream里读出的值（放进buffer里)不是-1（到达文件结尾），就把buffer里的值写到writerStram里
        try{
            //每次读取512个字节，最后一次可能不够512个
            //writerStrem就代表这个要写入数据的文件，
            while ((numberRead = readerStream.read(buffer))!=-1){
                //将buffer中的内容写到writerStream代表的文件中，0-numberRead是要写buffer里这个范围的数据
                writerStream.write(buffer,0,numberRead);
            }
        }catch (IOException ioe){
            System.out.println("error reading/writing file");
        }finally {
            try{
                readerStream.close();
                writerStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("copy l file");
    }
}
