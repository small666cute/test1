package com.setlistmap;

import java.util.*;

//treeset
public class SortableTeam {
    public static void main(String[] args) {
        SortedSet<Player> ageSortedTeam = new TreeSet<>();
        //插入元素的时候，排序方式是按照类（player)定义的比较器的方式排序的，这里是年龄升序
        ageSortedTeam.add(new Player("p1", 1));
        ageSortedTeam.add(new Player("p2", 5));
        ageSortedTeam.add(new Player("p3", 2));
        ageSortedTeam.add(new Player("p4", 10));
        ageSortedTeam.add(new Player("p5", 5));
        ageSortedTeam.add(new Player("p6", 2));
        ageSortedTeam.add(new Player("p7", 7));
        ageSortedTeam.add(new Player("p8", 0));
        ageSortedTeam.add(new Player("p9", 1));
        System.out.println("按年龄排序");
        printSet(ageSortedTeam);
        System.out.println("--------------------------");
        //重新实现Player的compare方法，按名字字母排序
        //这儿TreeSet的参数是Comparator的实例，comparator和compare和compareTo是个什么关系？
        SortedSet<Player> nameSortedTeam = new TreeSet<Player>(new Comparator<Player>() {
            public int compare(Player a, Player b) {
                return a.getName().compareTo(b.getName());
            }
        });
        //使用addall，把上面那个set的元素都插入
        nameSortedTeam.addAll(ageSortedTeam);
        System.out.println("按照名字字母顺序排序");
        printSet(nameSortedTeam);
        System.out.println("---------------------");
    }

    static void printSet(Set set) {
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Player player = (Player) iterator.next();
            System.out.println(player.getName() + "-年龄:" + player.getAge());
        }
    }

    private static class Player implements Comparable<Player> {
        private String name;
        private int age;

        public Player(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        //返回当前对象年龄和接收到的对象年龄差别，这个决定了默认的排序方式
        @Override
        public int compareTo(Player other) {
            return age - other.age;
        }
    }
}
