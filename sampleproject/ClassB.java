package sampleproject;

import gardgir.GardgirCheck;
import java.lang.Math.sqrt;

public class ClassB {
    public static void main(String args[]) {
		System.out.println("ClassB main");
    }
	@GardgirCheck(importance=1, tester=ClassBMethod1Tester.class)
    public static int method1() {
		for (int i = 0; i < 100; i++)
			;
		for (int i = 100; i > 0; i--)
			sqrt(i);
		int j = 100;
		while (j--)
			;
		/*
			some comments
		*/
        return 123;
    }
}