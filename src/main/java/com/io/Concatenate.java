package com.io;

import java.io.*;

//文件级联
public class Concatenate {
    //这个String... fileName是可变参数列表，参数可变，并且每个参数都是string类型，注意：可变参数必须是形参列表中的最后一个参数
    //编译器会把这个可变参数列表转换成数组，然后就可以用foreach来迭代了
    public static void concenateFile(String... fileName) {
        String str = null;
        //这个在try（）括号里写一个流的语句，叫java try(){}catch(){}自动资源释放，语句结束后会自动关闭资源
        //打开一个writer，以把数据往writer里写，迭代访问每个文件，对每个文件都打开一个reader，这样把文件里的数据按行读出来，再按行写进writer里面
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("d:/combinedFile.txt"));){
            for(String name : fileName){
                try(BufferedReader reader = new BufferedReader(new FileReader(name));){
                    while ((str=reader.readLine())!=null){
                        writer.write(str);
                        //writer.newLine();
                    }
                }catch (IOException e){
                    System.out.println("error reading/writing file");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if(args.length<0){
            System.out.println("need 几个文件把它们的内容合在一起");
            System.exit(0);
        }
        concenateFile(args);
        System.out.println("合起来的文件叫combinedFile.txt");
    }
}
