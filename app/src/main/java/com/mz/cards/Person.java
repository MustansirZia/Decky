package com.mz.cards;

import android.support.annotation.NonNull;

/**
 * Written with ❤! By M on 02/04/17.
 */

public class Person implements Comparable<Person> {

    private String name, company, position;
    private PersonType type;

    public Person(String name, String company, String position, PersonType type) {
        this.name = name;
        this.company = company;
        this.position = position;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public PersonType getType() {
        return type;
    }

    public void setType(PersonType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", position='" + position + '\'' +
                ", type=" + type +
                '}';
    }

    @Override
    public int compareTo(@NonNull Person o) {
        return this.getName().compareTo(o.getName());
    }
}

