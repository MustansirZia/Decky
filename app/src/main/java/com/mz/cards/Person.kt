package com.mz.cards

/**
 * Written with ❤! By M on 02/04/17.
 */

class Person(var id: Int, var name: String, var company: String, var position: String, var type: PersonType) : Comparable<Person> {

    override fun toString(): String {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", position='" + position + '\'' +
                ", type=" + type +
                '}'
    }

    override fun compareTo(other: Person): Int {
        return this.name.compareTo(other.name)
    }
}

