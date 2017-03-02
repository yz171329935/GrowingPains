package com.marco.groovy.GroovyInAction_PDF.P2.P2_3

/**
 * Created by marco on 16/10/8.
 */
class RangeDemo {

    public static void main(String[] args) {
        def x = 1..10
        assert x.contains(5)
        assert !x.contains(15)
        assert x.size() == 10
        assert x.from == 1
        assert x.to == 10
        assert x.reverse() == 10..1
    }
}
