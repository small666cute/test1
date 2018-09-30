package com.lianxi;

import java.util.LinkedList;

//这是小元素
class Data {
    //假装里面有一个数据
    Integer data;

    public Data(Integer data) {
        this.data = data;
    }
}

class Node {
    //类型
    Integer type;
    //每个节点都是一个链表
    LinkedList<Data> node_list;
    //这个小链表的长度，是3
    int size;

    public Node(Integer type, LinkedList<Data> node_list, int size) {
        this.type = type;
        this.node_list = node_list;
        this.size = size;
    }
}

public class Lianxi1 {
    //有多少种
    final static int KIND_NUM = 100;
    //每种有几个
    final static int PER_KIND = 3;
    //这个是大链表的长度，这是一个随时变化的数值
    volatile Integer length;
    //存储原始数据的数组
    private static Data dataArray[][];


    public static void main(String[] args) {
        //每个节点是小链表，组成了长度为100的大链表
        LinkedList<Node> test_list = new LinkedList<>();
        //然后初始化这个大链表，往里面放数据
        /*//下面是示例的思路
        //下面放试着放一个数据进去
        //首先弄3个小数据,他们的值都一样
        Data data1_1 = new Data(1);
        Data data1_2 = new Data(1);
        Data data1_3 = new Data(1);
        //然后把这三个小元素放到小链表里面
        LinkedList<Data> nodeList1 = new LinkedList<Data>();
        nodeList1.add(0, data1_1);
        nodeList1.add(1, data1_2);
        nodeList1.add(2, data1_3);
        //然后用小链表构造这个节点
        Node node1 = new Node(1, nodeList1, nodeList1.size());
        //然后把这个节点放进去
        test_list.add(0, node1);
        //然后重复这个操作*/
        try {
            if(!putDataToBlist(test_list)){
                throw new Exception("创建链表出错");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //然后创建线程，放到一个数组里
        Thread users[]=new Thread[10];
        for(int i=0;i<10;i++){
            users[i]=new Thread(new User(test_list));
            users[i].start();
        }
    }

    //下面这些用几个方法分隔这个操作，不过还没有写完
    //把100个node放到大链表里
     private static Boolean putDataToBlist(LinkedList<Node> Biglist) {
        try {
            for (int i = 0; i < KIND_NUM; i++) {
                Biglist.add(createNode(i));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //造一个小节点Node
    private static Node createNode(int i) {
        return new Node(i, putDatToSlist(i), putDatToSlist(i).size());

    }

    //把3个Data放到小链表里,生成一个小链表
    private static LinkedList<Data> putDatToSlist(int i) {
        //然后把这三个小元素放到小链表里面
        LinkedList<Data> nodeList = new LinkedList<Data>();
        nodeList.add(0, dataArray[i][0]);
        nodeList.add(1, dataArray[i][1]);
        nodeList.add(2, dataArray[i][2]);
        return nodeList;
    }

    //100行，3列,生成一个二维矩阵
    private static void generateData() {
        dataArray = new Data[KIND_NUM][PER_KIND];
        for (int i = 0; i < KIND_NUM; i++) {
            for (int j = 0; j < PER_KIND; j++) {
                dataArray[i][j] = new Data(i);
            }
        }
    }
    //创建实现Runnable接口的内部类
    private static class User implements Runnable{
        LinkedList<Node> test_list ;

        public User(LinkedList<Node> test_list) {
            this.test_list = test_list;
        }

        @Override
        public void run() {
            //根据length生成随机数
            //根据随机数寻找
            //找到了对应节点，若小链表下有数据就取
            //取完了删除该节点，并且如果是它取了这个小链表的最后一个元素，就负责删除这个节点
        }
    }
}
