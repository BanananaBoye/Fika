public class Latte extends Coffee{

    private final int min = 25;
    private final int max = 35;
    private final String name = "latte";

    public Latte() {
        energyCalculator(min,max);
    }

    public String getName(){return name;}
}
