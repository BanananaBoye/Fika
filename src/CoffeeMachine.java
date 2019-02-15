import java.util.ArrayList;

public class CoffeeMachine {

    ArrayList<Coffee> reservedCoffee;

    public CoffeeMachine(){
        reservedCoffee = new ArrayList<>();
        fillReseredCoffee(20);

        System.out.println("There is "+reservedCoffee.size()+" reserved Coffees");
       //for (Coffee c:reservedCoffee){
       //    System.out.println("Energy: \t"+c.getEnergyValue());
       //}
    }

    public void fillReseredCoffee(int amount){
        for (int i =0;i<amount;i++){

            int randDrink = (int) (Math.random()*3);

            switch (randDrink){
            case 0:
                reservedCoffee.add(new Latte());
                break;
            case 1:
                reservedCoffee.add(new BlackCoffee());
                break;
            case 2:
                reservedCoffee.add(new Cappuccino());
                break;
            }
        }
    }
    public int drinkCoffee(){
        if(reservedCoffee.size() != 0) {
            int coffeeIndex = (int) (Math.random() * reservedCoffee.size());
            int coffeeEnergyValue = reservedCoffee.get(coffeeIndex).getEnergyValue();
            randomRefill();
            reservedCoffee.remove(coffeeIndex);
            return coffeeEnergyValue;
        }else return 0;
    }
    private void randomRefill() {
        double chance = Math.random();
        if (chance < 0.2) {
            fillReseredCoffee(5);
        }
    }
}
