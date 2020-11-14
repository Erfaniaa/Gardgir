package sampleproject;

import gardgir.GardgirCheck;

public class ClassA {
    public static void main(String args[]) {
        System.out.println("ClassA main");
    }
    @GardgirCheck(importance=100, tester=ClassAMethod1Tester.class)
    public static int method1() {
        return ClassB.method1();
    }
}