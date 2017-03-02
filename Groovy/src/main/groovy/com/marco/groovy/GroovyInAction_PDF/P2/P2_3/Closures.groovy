package com.marco.groovy.GroovyInAction_PDF.P2.P2_3

/**
 * Created by marco on 16/10/8.
 */
class Closures {
    public static void main(String[] args) {

        [1, 2, 3].each { entry -> println entry }

        def totalClinks = 0
        def partyPeople = 100
        1.upto(partyPeople) { guestNumber ->
            totalClinks += guestNumber-1
        }
        assert totalClinks == (partyPeople * (partyPeople-1)) / 2
    }
}
