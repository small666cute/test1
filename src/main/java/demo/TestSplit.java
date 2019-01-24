package demo;

import org.apache.commons.lang3.StringUtils;

public class TestSplit {
    public static void main(String[] args) {
        String sc252="1";
        String[] temp=sc252.split(".");
        System.out.println(temp[0]);
        //String sc252Deci = sc252.split(".")[0];
        //System.out.println(sc252Deci);
        String[] test=StringUtils.split(sc252,".");
    }
}
