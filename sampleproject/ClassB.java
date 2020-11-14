package sampleproject;

import gardgir.GardgirCheck;
import java.lang.Math;

public class ClassB {
    public static void main(String args[]) {
		System.out.println("ClassB main");
    }
	@GardgirCheck(importance=1, tester=ClassBMethod1Tester.class)
    public static int method1() {
		for (int i = 0; i < 100; i++)
			;
		for (int i = 100; i > 0; i--)
			Math.sqrt(i);
		int j = 100;
		while (j > 0)
			j--;
		/*
			some comments
		*/
        return 123;
    }
}