package com.marco.groovy.integration;

/**
 * Created by marco on 16/9/19.
 */
public class GroovyClassLoaderTest {

    public static void main(String[] args) {
        def gcl = new GroovyClassLoader()
        def clazz = gcl.parseClass('class Foo { void doIt() { println "ok" } }')
        def clazz2 = gcl.parseClass('class Foo { void doIt() { println "ok" } }')

        assert clazz.name == 'Foo'
        assert clazz2.name == 'Foo'
        assert clazz != clazz2
        def o = clazz.newInstance()
        o.doIt()

    }
}
