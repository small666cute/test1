package demo;

public class SplitStrings {
    public static void main(String[] args) {
        String str_before="age:0,carHailingDriver:0,carInsDemand:0,carInsPurchased:0,carOwner:0,careBodyBuilding:0,careHealth:0,\n" +
                "        city:南京市,cityLevel:n1,courier:0,creditCardCrowd:0,dspTag:0,endowmentInsDemand:0,\n" +
                "        foodDeliver:0,frequentAir:0,gender:1,graduate:0,highEndDrink:0,homeIsCity:0,houseAgency:0,\n" +
                "        houseKeeping:0,insuranceAgency:0,insuranceDemand:0,insuranceGroup:0,insuranceMart:0,\n" +
                "        lifeInsDemand:0,lifeInsPurchased:0,likeOnlineShoping:1,likeStudy:0,literacyLevel:1,\n" +
                "        logisticsDrivers:0,mParents:0,matchTag:1,noCommon:0,noSocial:0,pParents:0,province:江苏省,\n" +
                "        rentingCrowd:0,stableWorkFlag:1,staff:0,students:0,workIsCity:1";
        StringBuffer stringBuffer=new StringBuffer();
        for(String str:str_before.split(",")){
            //System.out.println(str.replaceAll(" ","").replace("\n",""));
            //StringBuffer stringBuffer=new StringBuffer();
            //stringBuffer.append("public static final String ").append("VRBO_").append(str.replaceAll(" ","").replace("\n","").split(":")[0].toUpperCase()).append("=\"").append(str.replaceAll(" ","").replace("\n","").split(":")[0]).append("\";");
            stringBuffer.append("FinTellVrbo.").append("VRBO_").append(str.replaceAll(" ","").replace("\n","").split(":")[0].toUpperCase()).append(",");
            //System.out.println(stringBuffer.toString());
        }
        Object value;
        value="null";
        stringBuffer.append(value);
        System.out.println(stringBuffer.toString());


    }
}
