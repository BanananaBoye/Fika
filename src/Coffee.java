public abstract class Coffee {

    private int energyValue;

    public Coffee() { }

    public int getEnergyValue() {
        return energyValue;
    }
      // Will calculate the energy level based on the coffee sort.
    public void energyCalculator(int max, int min){
       int temp = (int) (Math.random() * (max - min+1)) + min;
       energyValue = temp;
    }
    public abstract String getName();

}
