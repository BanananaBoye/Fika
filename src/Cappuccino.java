public class Cappuccino extends Coffee{

    private final int min = 20;
    private final int max = 30;
    private final String name = "cappuccino";

    public Cappuccino() {
        energyCalculator(min,max);
    }

    public String getName(){return name;}
}
