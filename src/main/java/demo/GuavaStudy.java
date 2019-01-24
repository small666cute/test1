/*
package demo;


import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;

import java.util.Iterator;
import java.util.Map;

public class GuavaStudy {
    public static void main(String[] args) {
        //使用Joiner,split来更好的操作字符串
        //对基本型，int这种，有很好的指针，Ints.方法很多很好用，能直接找最大值啊、是否contain等等
        String[] subdirs = {"usr", "local", "lib"};
        String directory = Joiner.on("/").join(subdirs);
        //System.out.println(directory);
        int[] numbers = {1, 2, 3, 5};
        String numbersAsString = Joiner.on(";").join(Ints.asList(numbers));
        System.out.println(numbersAsString);
        String numbersAsStringDirectly = Ints.join(";", numbers);
        System.out.println(numbersAsStringDirectly);
        String testsplitString = "foo , what,,,,more,";
        Iterable split = Splitter.on(",").split(testsplitString);
        Iterator iterator = split.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        //显然上面的输出不够好，一堆空格，我们换个控制
        Iterable<String> split_ex = Splitter.on(",").omitEmptyStrings().trimResults().split(testsplitString);
        //用for遍历
        for (Iterator iter = split_ex.iterator(); iter.hasNext(); ) {
            System.out.println(iter.next());
        }

        //function

        Map eurPriceMap = Maps.newHashMap();
        //没懂，，，
        Map usdPriceMap = Maps.transformValues(eurPriceMap, new Function() {
            double eurToUsd = 1.4888;

            public Double apply(final Double from) {
                return from * eurToUsd;
            }
        });
    }
}
*/
