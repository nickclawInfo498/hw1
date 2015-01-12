package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {

  private static int instanceCount = 0;

  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;

  /**
   * @constructor
   */
  public Person() {
    this("", 0, 0.0d);
  }

  /**
   * @constructor
   * @param name
   * @param age
   * @param salary
   */
  public Person(String name, int age, double salary) {
    this.name = name;
    this.age = age;
    this.salary = salary;
    this.ssn = "";
    this.instanceCount++;
  }

  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  /**
   * Calculates the users bonus
   * @return
   */
  public double calculateBonus() {
    return salary * 1.10;
  }

  /**
   * Returns the persons name as a judge
   * @return
   */
  public String becomeJudge() {
    return "The Honorable " + name;
  }

  /**
   * Returns the persons age 10 years into the future
   * @return
   */
  public int timeWarp() {
    return age + 10;
  }

  /**
   * @override
   * @return
   */
  public String toString() {
    return "[Person name:" + getName() + " age:" + getAge() + " salary:" + getSalary() + "]";
  }

  /**
   * Compares this person to another for sorting
   * @param p
   * @return
   */
  public int compareTo(Person p) {
    return 0;
  }

  /**
   * Test for equality
   * Does not test against salary
   * @param p
   * @return
   */
  public boolean equals(Object p) {
    if (p instanceof Person) {
      Person person = (Person)p;
      return getName().equals(person.getName()) && getAge() == person.getAge();
    }
    return false;
  }

  //
  // Getters/Setters
  //

  /**
   * Gets the age of the person
   * @return
   */
  public int getAge() {
    return age;
  }

  /**
   * Sets the age of the person
   * Fires property change event for any listeners
   * @param age
   */
  public void setAge(int age) throws IllegalArgumentException {
    if (age < 0) {
      throw new IllegalArgumentException();
    }

    int old = this.age;
    this.age = age;

    this.pcs.firePropertyChange("age", old, age);
    propertyChangeFired = true;
  }

  /**
   * Gets the name of the person
   * @return
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the person
   * Fires property change event for any listeners
   * @param name
   */
  public void setName(String name) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException();
    }

    String old = this.name;
    this.name = name;

    this.pcs.firePropertyChange("name", old, name);
    propertyChangeFired = true;
  }

  /**
   * Gets the salary of the person
   * @return
   */
  public double getSalary() {
    return salary;
  }

  /**
   * Sets the salary of a person
   * Fires property change event for any listeners
   * @param salary
   */
  public void setSalary(double salary) {
    double old = this.salary;
    this.salary = salary;

    this.pcs.firePropertyChange("salary", old, salary);
    propertyChangeFired = true;
  }

  /**
   * Gets the SSN of the person
   * @return
   */
  public String getSSN() {
    return ssn;
  }

  /**
   * Sets the SSN of the person
   * @param ssn
   */
  public void setSSN(String ssn) {
    String old = this.ssn;
    this.ssn = ssn;

    this.pcs.firePropertyChange("ssn", old, ssn);
    propertyChangeFired = true;
  }

  //
  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }

  //
  // Public subclass to sort people by age
  //
  public static class AgeComparator implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
      return p1.getAge() - p2.getAge();
    }
  }
  
  public static List<Person> getNewardFamily() {
    List<Person> list = new ArrayList<Person>();
    list.add(new Person("Ted", 41, 250000));
    list.add(new Person("Charlotte", 43, 150000));
    list.add(new Person("Michael", 22, 10000));
    list.add(new Person("Matthew", 15, 0));
    return list;
  }

  public static int count() {
    return instanceCount;
  }
}
