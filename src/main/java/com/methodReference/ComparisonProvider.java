package com.methodReference;

import java.util.Arrays;

public class ComparisonProvider {
    public int compareByName(Person a ,Person b){
        return a.getName().compareTo(b.getName());
    }
    public int compareByAge(Person a,Person b){
        return a.getBirthday().compareTo(b.getBirthday());
    }
    ComparisonProvider myComparisonProvider = new ComparisonProvider();
}
