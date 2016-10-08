package com.marco.groovy.GroovyInAction_PDF.P2_3

/**
 * Created by marco on 16/10/8.
 */
class NumbersAreObj {
    public static void main(String[] args) {
        def x = 1
        def y = 2
        assert x + y == 3
        assert x.plus(y) == 3
        assert x instanceof Integer
    }
}
