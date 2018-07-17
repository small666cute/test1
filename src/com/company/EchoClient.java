package com.company;
import java.io.*;
import java.net.*;
public class EchoClient {
    public static void main(String args[])throws  IOException {
        Socket echoSocket=null;
        PrintWriter out=null;
        BufferedReader in=null;
        try{
            echoSocket=new Socket("localhost",1112);
            out=new PrintWriter(echoSocket.getOutputStream(),true);
            in=new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        }catch (UnknownHostException e){
            System.err.println("buzhidaozhuji:localhost");
            System.exit(1);
        }
        System.out.println(in.readLine());
        //System.out.println(in.readLine());
        BufferedReader stdIn=new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        while ((userInput=stdIn.readLine())!=null){
            out.println(userInput);
            System.out.println(in.readLine());
        }
        out.close();
        in.close();
        echoSocket.close();
    }
}
