package iteration1.src.Models;

public abstract class Person {

    private String name;

    public Person(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
