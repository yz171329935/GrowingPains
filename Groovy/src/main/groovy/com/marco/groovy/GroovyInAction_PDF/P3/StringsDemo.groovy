package com.marco.groovy.GroovyInAction_PDF.P3

/**
 * Created by marco on 16/10/31.
 */
class StringsDemo {

    public static void main(String[] args) {
        println 'hello, marco'

        def name = 'marco'
        println "hello, $name"

        println ''' ======total: $0.02========'''

        def line = 'lalla'
        println """ frist $line second $line third $line"""
    }
}
