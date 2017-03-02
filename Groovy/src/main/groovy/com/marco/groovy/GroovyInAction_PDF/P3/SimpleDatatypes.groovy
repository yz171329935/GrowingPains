package com.marco.groovy.GroovyInAction_PDF.P3

/**
 * Created by marco on 16/10/28.
 */
class SimpleDatatypes {

    public static void main(String[] args) {

        println(
                // invalid Java
                (60 * 60 * 24 * 365).toString()
        )
        int secondsPerYear = 60 * 60 * 24 * 365;
        println(
                secondsPerYear.toString()
        ); // invalid Java

        new Integer(secondsPerYear).toString();
        assert "abc" - "a" == "bc" // invalid Java


        assert 'ABCDE'.indexOf(67) == 2

        Integer myInt = new Object()
        println myInt
    }

}
