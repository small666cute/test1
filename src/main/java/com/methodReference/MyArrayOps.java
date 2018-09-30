package com.methodReference;

public class MyArrayOps {
    //指定泛型类型参数
    public static <T> int countMatching(T[] vals,T v){
        int count = 0;
        for(int i=0;i<vals.length;i++){
            if(vals[i]==v){
                count++;
            }
        }
        return count;
    }
}
