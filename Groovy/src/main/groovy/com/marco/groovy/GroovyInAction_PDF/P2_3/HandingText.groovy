package com.marco.groovy.GroovyInAction_PDF.P2_3

/**
 * Created by marco on 16/10/8.
 */
class HandingText {

    public static void main(String[] args) {
        def nick = 'ReGina'
        def book = 'Groovy in Action, 2nd ed.'
        assert "$nick is $book" == 'ReGina is Groovy in Action, 2nd ed.'

        assert '12345' =~ /\d+/
        assert '12345a' =~ /\d+\w/
        assert 'xxxxx' == '12345'.replaceAll(/\d/, 'x')
    }
}
