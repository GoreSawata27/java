class Calc {

    public int add(int a, int b) {
        return a + b;
    }

    public int sub(int a, int b) {
        return a - b;
    }
}

class AdvCalc extends Calc {

    public double pow(int a, int b) {
        return Math.pow(a, b);
    }
}

public class InheritanceCalc {
    public static void main(String[] args) {
        AdvCalc obj = new AdvCalc();

        System.out.println("Addition: " + obj.add(10, 5));
        System.out.println("Subtraction: " + obj.sub(10, 5));
        System.out.println("Power: " + obj.pow(2, 3));
    }
}