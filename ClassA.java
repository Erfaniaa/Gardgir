public class ClassA {
    public static void main(String args[]) {
        System.out.println("Salam");
    }
    @GardgirCheck(importance=50, tester=ClassAMethod1Tester.class)
    public static int method1() {
        return ClassB.method1();
    }
}