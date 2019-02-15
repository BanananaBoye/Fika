
  // This is a Thread aka a Person
public class Person extends Thread{

    private int energy;
    private String name;

    public Person(Runnable target, String name0,String name1) {
        super(target,name0);
        this.name = name1;
        startEnergy();
        System.out.println(name+" started with "+energy+" energy" );
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy += energy;
    }

    public String getPerson() {
        return name;
    }

    public void timeSubtraction(){
        this.energy -=10;
    }

    private void startEnergy(){
        energy = (int ) (Math.random()*90 + 30+1)-30;
    }

}
