package demo;

import com.google.common.collect.Sets;

import java.util.Set;

//尝试去重是不是根据值
public class Testquchong {
    public static void main(String[] args) {
        Set<String> set = Sets.newHashSet();
        String str1="aaa";
        String str2="aaa";
        String str3=new String("aaa");
        String str4=str3;
        set.add(str1);
        set.add(str2);
        set.add(str3);
        System.out.println(set.size());
    }
}

