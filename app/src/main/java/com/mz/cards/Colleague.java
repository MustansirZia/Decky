package com.mz.cards;

/**
 * Written with ❤! By M on 02/04/17.
 */

public class Colleague {

    private String name, company, position;

    public Colleague(String name, String company, String position) {
        this.name = name;
        this.company = company;
        this.position = position;
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

    @Override
    public String toString() {
        return "Colleague{" +
                "name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
