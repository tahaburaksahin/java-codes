// DJW-InherAnimal/Animal.java
 
public class Animal {
    protected String name;
    protected double weight;
      // no default constructor!
    public Animal(String n, double w) {
        name = n;
        weight = w;
    }
    public String getVoice() {
        return "?";
    }
    public static void voices(Animal[] animals) {
        for (Animal a : animals)
            System.out.println(a + " " + a.getVoice());
    }
    @Override
    public String toString() {
        return name + "(" + weight + ")";
    }
}
