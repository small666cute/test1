package com.setlistmap;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

//linkedlist
public class SoccerTeam {
    public static void main(String[] args) {
        //声明一个男性成员列表
        List<String> maleTeam = new LinkedList<>();
        //增加男性成员
        maleTeam.add("male1");
        maleTeam.add("male2");
        maleTeam.add("male3");
        maleTeam.add("male4");
        maleTeam.add("male5");
        System.out.println("male team: " + maleTeam);
        //声明一个女性成员列表
        List<String> femaleTeam = new LinkedList<>();
        femaleTeam.add("female1");
        femaleTeam.add("female2");
        femaleTeam.add("female3");
        femaleTeam.add("female4");
        femaleTeam.add("female5");
        System.out.println("female team: " + femaleTeam);
        //把女性成员加到男性成员列表中，混合的方式是交替混合
        //因为iterator不可以插入，只能移除。所以使用listiterator，它提供了add操作
        ListIterator<String> maleListIterator = maleTeam.listIterator();
        Iterator<String> femaleListIterator = femaleTeam.iterator();
        //交替插入进去
        while (femaleListIterator.hasNext()) {

            if (maleListIterator.hasNext()) {
                //System.out.println(maleListIterator);
                maleListIterator.next();
            }
            maleListIterator.add(femaleListIterator.next());
        }
        System.out.println("mixed team: " + maleTeam);
        //定义一个资格被取消的成员列表disqulify
        List<String> disqualify = new LinkedList<>();
        disqualify.add("male1");
        disqualify.add("female1");
        //然后从maleteam里面一处取消资格的成员
        maleTeam.removeAll(disqualify);
        System.out.println("qualified team: " + maleTeam);
    }
}
