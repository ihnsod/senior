package com.Ihnsod.basics.basics.collection;

import org.junit.Test;

import java.util.EnumSet;
import java.util.HashSet;

/**
 * @author Ihnsod
 * @create 2018-05-16 16:17
 **/
public class SetTest {

    /**
     * hashset
     */
    @Test
    public void test(){
        HashSet<String> hashSet = new HashSet<>();
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("heh");
        hashSet.addAll(hashSet1);
    }

     enum Week{
         Monday ,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday
     }

    @Test
    public void test1(){
        EnumSet<Week> enumSet = EnumSet.allOf(Week.class);
        System.err.println(enumSet);
        EnumSet<Week> enumSet1 = EnumSet.noneOf(Week.class);
        enumSet1.add(Week.Monday);
        enumSet1.add(Week.Thursday);
        System.err.println(enumSet1);
        EnumSet<Week> enumSet2 = EnumSet.complementOf(enumSet1);
        System.err.println(enumSet2);
    }
}
