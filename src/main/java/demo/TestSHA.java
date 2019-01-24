package demo;

public class TestSHA {
    public static final String PHONE_REGEX_MD5="^[0-9A-Za-z]{32}$";
    public static void main(String[] args) {
        String phone1="acf377e7a7e7c7fd6603a0a7991589bf";
        String phone2="6a47fc7fdef685dec65f16f6c25105af";
        String phone3="56b6ab12beddb461bd07da201ae6bd30";
        String phone4="93457u389";
        String phone5="d7663637595145cd48797c5c1fd1c30a";
        String regex="^[a-z0-9]+$";
        System.out.println(phone1.matches(PHONE_REGEX_MD5));
        System.out.println(phone2.matches(PHONE_REGEX_MD5));
        System.out.println(phone3.matches(PHONE_REGEX_MD5));
        System.out.println(phone4.matches(PHONE_REGEX_MD5));
    }
}
