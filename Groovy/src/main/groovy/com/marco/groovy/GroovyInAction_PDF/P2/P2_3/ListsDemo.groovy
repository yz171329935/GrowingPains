package com.marco.groovy.GroovyInAction_PDF.P2.P2_3

/**
 * Created by marco on 16/10/8.
 */
class ListsDemo {
    public static void main(String[] args) {
        def roman = ['', 'I', 'II', 'III', 'IV', 'V', 'VI', 'VII']

        assert roman[4] == 'IV'
        roman[8] = 'VIII'
        assert roman.size() == 9
    }
}
