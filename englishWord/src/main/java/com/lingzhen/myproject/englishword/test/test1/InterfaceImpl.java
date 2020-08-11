package com.lingzhen.myproject.englishword.test.test1;

import java.util.Arrays;
import java.util.Comparator;

public class InterfaceImpl<staitc> {

    public static void fun (InterfaceA a ){
        Person p = new Person();
        System.out.println(a.test1(p));
    }
    public static void main(String[] args) {
        //fun(p -> {return 1;});
        AAA aaa = new AAA();
        BBB bbb = new BBB();
        String str  = new String();

      //  a(aaa::compare);
        //a(bbb::compare);

        String [] stringArray = {"芭芭拉","詹姆斯","玛丽","约翰",
    "Patricia","Robert","Michael","Linda"};

        String s = new String();
        Arrays.sort(stringArray,String :: compareToIgnoreCase);

    }

    public static void a (Comparator<Integer> c){
        int i =1;
        int ii =2;
        System.out.println(c.compare(i,ii));
    }
}

class AAA implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return 1;
    }

}

class BBB implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return 2;
    }

}

class CCC implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return 2;
    }

}

