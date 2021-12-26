package iteration2.src.Models;

public abstract class Person {

    private String name;    //This data holds the name for given person(Student, Advisor)

    public Person(String name){
        this.name = name;
    } //Constructor for given person.

    public String getName(){
        return name;
    }  //Getter method for person's name.
}
