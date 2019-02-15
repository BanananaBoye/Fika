import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class PersonHandlerTest implements Runnable {
    // Sync acts as a second lock for the synchronized method. If not used, the person will "stand in line" for the
    // method and will not lose any energy in the process.
    private boolean sync = true; // TODO: Need a better solution.
    private ArrayList<Person> people;
    private CoffeeMachine coffeeMachine;

    // Construct an arrayList for the Threads aka Persons
    // Give them energy value and name for both the super and child class
    PersonHandlerTest() {
        people = new ArrayList<>();
        coffeeMachine = new CoffeeMachine();
        Person p1 = new Person(this, "Jon", "Jon");
        Person p2 = new Person(this, "Kim", "Kim");
        Person p3 = new Person(this, "Rey", "Rey");
        Person p4 = new Person(this, "Max", "Max");
        people.add(p1);people.add(p2);people.add(p3);people.add(p4);
        p1.start();p2.start();p3.start();p4.start();
    }

    @Override // Primary loop for the threads. Checks if energy-lvl and makes an decision
    public void run() {

        while (people.size() != 0) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            working();
        }
        System.out.println("Everyone have gone home... This job must suck donkey balls");
    }
      // Sync function, ONLY 1 thread can enter at the time.
      // Puts the Thread to sleep for 10 sec. After that it will get 100 energy.
    public synchronized void coffeeRoom() {
        System.out.println(Thread.currentThread().getName() + " has " + people.get(compare()).getEnergy() + " energy and goes to the coffee-room");
        sync = false;
        while (people.get(compare()).getEnergy() < 30) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (coffeeMachine.drinkCoffee() != 0) {
                people.get(compare()).setEnergy(coffeeMachine.drinkCoffee());
                System.out.println("The coffee machine has " + coffeeMachine.reservedCoffee.size() + " drinks left");
            } else {
                removePerson();
            }
        }
        System.out.println(Thread.currentThread().getName() + " leaves the coffee-room with " + people.get(compare()).getEnergy() + " energy");
        sync = true;
    }
      // Simple function to find the correct Person.
    private int compare() {
        int index = 0;
        for (int i = 0; i < people.size(); i++) {
            if (Thread.currentThread().getName().equals(people.get(i).getPerson())) {
                index = i;
                break;
            }
        }
        return index;
    }
      // Removes Person from array and stops the thread
    private void removePerson() {
        System.out.println("\n" + Thread.currentThread().getName() + ":   Fuck this, I'm going HOME" + "\n");
        people.get(compare()).stop();  // ...
        people.remove(compare());
    }
      // Person is working and losing energy
    private void working(){
        // Will freeze the loop without the sync
        if (people.get(compare()).getEnergy() < 30 && sync) {
            coffeeRoom();
            // "Remove the thread for the array and loop
        } else if (people.get(compare()).getEnergy() < 0) {
            removePerson();
            // Subtract -10 energy for each second.
        } else {
            people.get(compare()).timeSubtraction();
        }
    }
}

