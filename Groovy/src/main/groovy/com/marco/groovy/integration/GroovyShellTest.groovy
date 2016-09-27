package com.marco.groovy.integration

import org.codehaus.groovy.control.CompilerConfiguration

/**
 * Created by marco on 16/9/18.
 */
class GroovyShellTest {

    public static void main(String[] args) {

        def shell = new GroovyShell()
        def result = shell.evaluate '3*5'
        def result2 = shell.evaluate(new StringReader('3*5'))
        assert result == result2
        def script = shell.parse '3*5'
        assert script instanceof groovy.lang.Script
        assert script.run() == 15


        shareDataBetweenScriptAndApplication();

        testMyScript();

    }


    def static  shareDataBetweenScriptAndApplication(){
        def sharedData = new Binding()
        def shell = new GroovyShell(sharedData)
        def now = new Date()
        sharedData.setProperty('text', 'I am shared data!')
        sharedData.setProperty('date', now)

        String result = shell.evaluate('"At $date, $text"')

        assert result == "At $now, I am shared data!"



        shell.evaluate('foo=123')
//        shell.evaluate('int foo=123') // 这样写会当成局部变量

        try {
            assert sharedData.getProperty('foo')
        } catch (MissingPropertyException e) {
            println "foo is defined as a local variable"
        }


        def b1 = new Binding(x:3)
        def b2 = new Binding(x:4)
        def script = shell.parse('x = 2*x')
        script.binding = b1
        script.run()
        script.binding = b2
        script.run()
        assert b1.getProperty('x') == 6
        assert b2.getProperty('x') == 8
        assert b1 != b2
    }



    def static testMyScript(){
        def config = new CompilerConfiguration()
        config.scriptBaseClass = 'MyScript'

        GroovyShellTest gst = new GroovyShellTest()
        def shell = new GroovyShell(gst.class.classLoader, new Binding(), config)
        def script = shell.parse('greet()')
        assert script instanceof MyScript
        script.setName('Michel')
        assert script.run() == 'Hello, Michel!'
    }


    abstract class MyScript extends Script {
        String name

        String greet() {
            "Hello, $name!"
        }
    }
}
