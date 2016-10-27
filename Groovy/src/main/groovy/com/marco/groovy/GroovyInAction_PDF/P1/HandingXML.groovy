package com.marco.groovy.GroovyInAction_PDF.P1

/**
 * Created by marco on 16/9/30.
 */
class HandingXML {

    public static void main(String[] args) {
        def customers = new XmlSlurper().parse(new File("/Users/marco/IdeaProjects/GrowingPains/Groovy/src/main/resources/customers.xml"))
        for (customer in customers.corporate.customer){
            println "${customer.@name} works for ${customer.@company}"
        }

    }
}
