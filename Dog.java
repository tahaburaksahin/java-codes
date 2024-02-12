// DJW-InherAnimal/Dog.java
 
public class Dog extends Animal {
    public Dog(String name, double weight) {
        super(name, weight);
    }
    @Override
    public String getVoice() {
        return "Bow-Wow";
    }
    @Override
    public String toString() {
        return "Dog " + super.toString();
    }
}
