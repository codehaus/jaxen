package org.jaxen.javabean;

import java.util.HashSet;
import java.util.Set;

public class Person
{
    private String name;
    private int age;

    private Set brothers;

    public Person(String name,
                  int age)
    {
        this.name = name;
        this.age  = age;
        this.brothers = new HashSet();
    }

    public String getName()
    {
        return this.name;
    }

    public int getAge()
    {
        return this.age;
    }

    public void addBrother(Person brother)
    {
        this.brothers.add( brother );
    }

    public Set getBrothers()
    {
        return this.brothers;
    }

    public String toString()
    {
        return "[Person: " + this.name + "]";
    }
}
