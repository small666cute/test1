package com.io;

import java.io.*;

//接受命令行的字符文件名称，然后显示文件的内容到控制台
public class FileView {
    public static void main(String[] args) {
        int numberRead = 0;
        FileReader reader = null;
        PrintWriter writer = null;
        char buffer[] = new char[512];
        if(args.length<1){
            System.out.println("file view ,need filename");
            System.exit(0);
        }
        try{
            reader = new FileReader(args[0]);//args[0]是一个文件路径，要得到这个文件的引用，将要把这个文件的内容读出来，然后做点什么
            writer = new PrintWriter(System.out);//system.out表示控制台，将要把什么东西写到writer里面去
            while ((numberRead=reader.read(buffer))!=-1){
                writer.write(buffer,0,numberRead);
            }
        }catch (FileNotFoundException fe){
            System.out.println(fe.getMessage());
            System.exit(0);
        }catch (IOException ioe){
            System.out.println("error reading/writing file");
        }finally {
            try{
                reader.close();
                writer.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
