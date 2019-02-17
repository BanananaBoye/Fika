public class BlackCoffee extends Coffee{

    private final int min = 20;
    private final int max = 40;
    private final String name = "black coffee";

    public BlackCoffee() {
        energyCalculator(max,min);
    }

    public String getName(){return name;}
}
