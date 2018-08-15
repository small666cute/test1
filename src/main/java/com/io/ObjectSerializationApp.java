package com.io;

import java.io.*;

//把一个类序列化
public class ObjectSerializationApp {
    public static void main(String[] args) {
        ObjectOutputStream objectWriter = null;
        ObjectInputStream objectReader = null;
        try{
            //这个new会创建student.dat新文件，如果存在同名文件，则覆盖原文件
            //？找了一些没找着可以”追加写“的构造方法什么的
            objectWriter = new ObjectOutputStream(new FileOutputStream("student.dat"));
            objectWriter.writeObject(new Student("fn1","ln1",1));
            objectWriter.writeObject(new Student("fn2","ln2",2));
            objectWriter.writeObject(new Student("fn3","ln3",3));
            System.out.println("打印数据库里的student列表");
            objectReader = new ObjectInputStream(new FileInputStream("student.dat"));
            for(int i = 0;i<3;i++){
                System.out.println(objectReader.readObject());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                objectReader.close();
                objectWriter.close();
            }catch (IOException ie){
                ie.printStackTrace();
            }
        }
    }
}

//实现Serializable接口
    class Student implements Serializable{
        private String firstName;
        private String lastName;
        private int id;

        public Student(String firstName, String lastName, int id) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.id = id;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", id=" + id +
                    '}';
        }
    }
