package com.fanxing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class FamousQuotes {
    private static ArrayList listofFamousQuotes;
    private static ArrayList<String > listofFamousQuotesTypechecked;

    public static void main(String[] args) {
        FamousQuotes app = new FamousQuotes();
        System.out.println("没有使用泛型\n");
        app.buildList();
        app.printList();
        System.out.println();

        System.out.println("有泛型\n");
        app.buildCheckedList();
        app.printCheckedList();

        System.out.println("\nnon-generics version of expurgate\n");
        String strAuthor="w c";
        System.out.println("after removing quotes by "+strAuthor);
        app.expurgate(listofFamousQuotes,"w c");
        app.printList();

        System.out.println("\ngenerics version of expurgate\n");
        System.out.println("after removing quotes by"+strAuthor);
        app.expurgateCheckedList(listofFamousQuotesTypechecked,strAuthor);
        app.printCheckedList();
    }
    void buildList(){
        listofFamousQuotes = new ArrayList();
        listofFamousQuotes.add("mg1-m g");
        listofFamousQuotes.add("wc1-w c");
        listofFamousQuotes.add("cc1-c c");
    }
    void buildCheckedList(){
        listofFamousQuotesTypechecked = new ArrayList<String>();
        listofFamousQuotesTypechecked.add("mg1-m g");
        listofFamousQuotesTypechecked.add("wc1-w c");
        listofFamousQuotesTypechecked.add("cc1-c c");
    }
    void printList(){
        Iterator listIterator = listofFamousQuotes.iterator();
        while (listIterator.hasNext()){
            String quote = (String)listIterator.next();
            System.out.println(quote);
        }
    }
    void printCheckedList(){
        Iterator<String> quoteIterator = listofFamousQuotesTypechecked.iterator();
        while (quoteIterator.hasNext()){
            String quote = quoteIterator.next();
            System.out.println(quote);
        }
    }
    void expurgate(Collection c,String strAuthor){
        for(Iterator i=c.iterator();i.hasNext();){
            if(((String)i.next()).contains(strAuthor)){
                i.remove();
            }
        }
    }
    void expurgateCheckedList(Collection<String> c,String strAuthor){
        for(Iterator<String> i=c.iterator();i.hasNext();){
            if(i.next().contains(strAuthor)){
                i.remove();
            }
        }
    }
}
