// DJW-InherAnimal/Cat.java
 
public class Cat extends Animal {
    public Cat(String name, double weight) {
        super(name, weight);
    }
    @Override
    public String getVoice() {
        return "Miaou-Miaou";
    }
    @Override
    public String toString() {
        return "Cat " + super.toString();
    }
}
