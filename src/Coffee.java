public abstract class Coffee {

    private int energyValue;

    public Coffee() { }

    public int getEnergyValue() {
        return energyValue;
    }

    public void energyCalculator(int max, int min){
       int temp = (int) (Math.random() * (max - min+1)) + min;
       energyValue = temp;
    }

}
