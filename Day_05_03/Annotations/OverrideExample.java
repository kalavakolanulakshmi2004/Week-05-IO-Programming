package org.example;
class Animal {
    public void makeSound() {
        System.out.println("Animal makes a sound");
    }
}
class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Dog barks");
    }
}
public class OverrideExample {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.makeSound();
    }
}