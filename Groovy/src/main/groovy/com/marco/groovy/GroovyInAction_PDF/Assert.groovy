package com.marco.groovy.GroovyInAction_PDF

/**
 * Created by marco on 16/10/6.
 */
class Assert {

    public static void main(String[] args) {
        assert (true)
        assert 1 == 1

        def x = 1
        assert x == 1

        def y = 1; assert y == 1


        def a = 5, b = 9;
//        assert b == a + b

        assert x == 'hey ,fdfdfd x'

    }

}
