import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

  // PersonHandler will act as the Persons " behavior ".
public class PersonHandler implements Runnable{
      // Sync acts as a second lock for the synchronized method. If not used, the person will "stand in line" for the
      // method and will not lose any energy in the process.
    boolean sync;
    ArrayList<Person> t;

      // Construct an arrayList for the Threads aka Persons
      // Give them energy value and name for both the super and child class
    PersonHandler(){
        t = new ArrayList<>();
        Person p1 = new Person(this,90,"Jon");
        Person p2 = new Person(this, 50,"Kim");
        Person p3 = new Person(this, 70,"Rey");
        p1.setName("Jon");
        p2.setName("Kim");
        p3.setName("Rey");
        t.add(p1);
        t.add(p2);
        t.add(p3);

        sync = true;

        p1.start();
        p2.start();
        p3.start();

    }

    // Primary loop for the threads. Checks if energy-lvl and makes an decision
    @Override
    public void run() {
        while(true) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
              // Will freeze the loop without the sync
            if (t.get(cp()).getEnergy() <= 30 && sync) {
                testFunc();
              // "Remove the thread for the array and loop
            }else if (t.get(cp()).getEnergy() <= 0){
                System.out.println(Thread.currentThread().getName() + ":   ###  DEAD  ###");
                t.get(cp()).stop();
                t.remove(cp());
                // Subtract -10 energy for each second.
            }else{t.get(cp()).timeSubraction();}
            System.out.println(Thread.currentThread().getName() + ":   Slept for 2 sec,\t Current Energy " + t.get(cp()).getEnergy() + "\t And sync is : " + sync);
        }
    }
      // Sync function, ONLY 1 thread can enter at the time.
      // Puts the Thread to sleep for 10 sec. After that it will get 100 energy.
    public synchronized void testFunc(){
        System.out.println(Thread.currentThread().getName() + ":   Going in to SYNCHRONIZED function");
        sync = false;
        try{TimeUnit.SECONDS.sleep(10);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        t.get(cp()).setEnergy(100);
        sync = true;
        System.out.println(Thread.currentThread().getName() + ":   Leaving SYNCHRONIZAED function ");

    }

    // Simple function to find the correct Person.
    public int cp(){
        int index = 0;
        for (int i =0;i< t.size();i++){
            if(Thread.currentThread().getName().equals(t.get(i).getPerson())){
                index = i;
                break;
            }
        }
        return index;
    }

}
