
  // This is a Thread aka a Person
public class Person extends Thread{

    int energy;
    String name;

    public Person(Runnable target, int energy, String name) {
        super(target);
        this.energy = energy;
        this.name = name;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public String getPerson() {
        return name;
    }

    public void timeSubraction(){
        this.energy -=10;
    }

    public void remove(){
        this.stop();
    }

}
