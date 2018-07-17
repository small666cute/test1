package com.company;
import java.io.*;
import java.net.*;
public class EchoServer {
    public static void main(String args[])throws IOException{
        ServerSocket serverSocket=null;
        PrintWriter out=null;
        BufferedReader in=null;
        try{
            serverSocket=new ServerSocket(1112);
            System.out.println("listen");
        }catch (IOException e){
            System.err.println("can't listen");
            System.exit(1);
        }
        Socket incoming=null;
        while (true){
            incoming=serverSocket.accept() ;
            out=new PrintWriter(incoming.getOutputStream(),true);
            in=new BufferedReader(new InputStreamReader(incoming.getInputStream()));
            out.println("hello");
            out.println("enter bye to exit");
            out.flush();
            while (true){
                String str=in.readLine();
                if(str==null){
                    break;
                }
                else {
                    out.println("ECHO:"+str);
                }
                out.flush();
                if(str.trim().equalsIgnoreCase("bye"))
                    break;
            }
        }
        //out.close();
        //in.close();
        //incoming.close();
        //byserverSocket.close();
    }
}

