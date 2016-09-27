package com.marco.groovy.integration

/**
 * Created by marco on 16/9/19.
 */
class GroovyScriptEngineTest {
    public static void main(String[] args) {
        def binding = new Binding()
        def tmpDir = "file:///Users/marco/IdeaProjects/GrowingPains/Groovy/src/main/groovy/com/marco/groovy/integration/";
        def engine = new GroovyScriptEngine([tmpDir.toURI().toURL()] as URL[])

        while (true) {
            def greeter = engine.run('ReloadingTest.groovy', binding)
            println greeter.sayHello()
            Thread.sleep(1000)
        }
    }
}
