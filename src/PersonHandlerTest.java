import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class PersonHandlerTest implements Runnable {
    // Sync acts as a second lock for the synchronized method. If not used, the person will "stand in line" for the
    // method and will not lose any energy in the process.
    boolean sync; // TODO: Need a better solution.
    ArrayList<Person> t;
    CoffeeMachine coffeeMachine;

    // Construct an arrayList for the Threads aka Persons
    // Give them energy value and name for both the super and child class
    PersonHandlerTest() {
        t = new ArrayList<>();
        Person p1 = new Person(this, "Jon", "Jon");
        Person p2 = new Person(this, "Kim", "Kim");
        Person p3 = new Person(this, "Rey", "Rey");
        Person p4 = new Person(this, "Max", "Max");
        t.add(p1);
        t.add(p2);
        t.add(p3);
        t.add(p4);

        coffeeMachine = new CoffeeMachine();

        sync = true;

        p1.start();
        p2.start();
        p3.start();
        p4.start();
    }

    @Override // Primary loop for the threads. Checks if energy-lvl and makes an decision
    public void run() {

        while (t.size() != 0) {
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
        System.out.println(Thread.currentThread().getName() + " has " + t.get(cp()).getEnergy() + " energy and goes to the coffee-room");
        sync = false;
        while (t.get(cp()).getEnergy() < 30) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (coffeeMachine.drinkCoffee() != 0) {
                t.get(cp()).setEnergy(coffeeMachine.drinkCoffee());
                System.out.println("The coffee machine has " + coffeeMachine.reservedCoffee.size() + " drinks left");
            } else {
                removePerson();
            }
        }
        System.out.println(Thread.currentThread().getName() + " leaves the coffee-room with " + t.get(cp()).getEnergy() + " energy");
        sync = true;
    }
      // Simple function to find the correct Person.
    private int cp() {
        int index = 0;
        for (int i = 0; i < t.size(); i++) {
            if (Thread.currentThread().getName().equals(t.get(i).getPerson())) {
                index = i;
                break;
            }
        }
        return index;
    }
      // Removes Person from array and stops the thread
    private void removePerson() {
        System.out.println("\n" + Thread.currentThread().getName() + ":   Fuck this, I'm going HOME" + "\n");
        t.get(cp()).stop();  // ...
        t.remove(cp());
    }
      // Person is working and losing energy
    private void working(){
        // Will freeze the loop without the sync
        if (t.get(cp()).getEnergy() < 30 && sync) {
            coffeeRoom();
            // "Remove the thread for the array and loop
        } else if (t.get(cp()).getEnergy() < 0) {
            removePerson();
            // Subtract -10 energy for each second.
        } else {
            t.get(cp()).timeSubraction();
        }
    }
}

