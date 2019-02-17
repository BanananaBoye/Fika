import java.util.ArrayList;

public class CoffeeMachine {

    public ArrayList<Coffee> reservedCoffee;
      // Coffee-machine will start with 20 different drinks
    public CoffeeMachine(){
        reservedCoffee = new ArrayList<>();
        fillReservedCoffee(20);
        System.out.println("There is "+reservedCoffee.size()+" reserved Coffees");
    }
      // Fills up the coffee-machine with the proper amount of coffees
    private void fillReservedCoffee(int amount){
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
      // remove a coffee from the machine and return a energy value.
    public int drinkCoffee(){
        if(reservedCoffee.size() != 0) {
            int coffeeIndex = (int) (Math.random() * reservedCoffee.size());
            int coffeeEnergyValue = reservedCoffee.get(coffeeIndex).getEnergyValue();

            randomRefill();
            reservedCoffee.remove(coffeeIndex);
            return coffeeEnergyValue;
        }else return 0;
    }
      // 20% chance to gain 5 coffees when drinking 1.
    private void randomRefill() {
        double chance = Math.random();
        if (chance < 0.2) {
            fillReservedCoffee(5);
            System.out.print("Magic coffee machine created ");
            for (int i = reservedCoffee.size()-5;i<reservedCoffee.size();i++){
                System.out.print(" "+ reservedCoffee.get(i).getName()+", ");
            }
            System.out.println("");
        }
    }
}
