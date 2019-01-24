package demo;

public class TestStirng {
    public static void main(String[] args) {
        String s="<string xmlns=\"http://tempuri.org/\">{\"CODE\":\"200\",\"KEY\":\"9e73a2a8675020a7fbf0c804625c201b\",\"PROVINCE\":\"福建\",\"CITY\":\"三明\",\"CYCLE\":\"2016-12-05--2018-12-05\",\"RESULTS\":[{\"TYPE\":\"EMR002\",\"DATA\":[{\"P_TYPE\":\"2\",\"PLATFORMCODE\":\"EM_0000180475\",\"REGISTERTIME\":\"2018/07/27 16:51:37\"}]},{\"TYPE\":\"EMR004\",\"DATA\":[]}]}</string>\n";
        String s1=s.substring(s.indexOf("{"), s.lastIndexOf("}")-1);
        //System.out.println(null==0);
        //System.out.println(s1);
        String str="123";
        System.out.println(str=="12");
    }
}
