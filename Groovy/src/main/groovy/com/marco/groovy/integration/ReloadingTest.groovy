package com.marco.groovy.integration


/**
 * Created by marco on 16/9/19.
 */

class Greeter {
    String sayHello() {
        def greet = new Depencency().message
        greet
    }
}

new Greeter()
