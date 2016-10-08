package com.marco.groovy.GroovyInAction_PDF

/**
 * Created by marco on 16/9/30.
 */
class PrintList {

    public static void main(String[] args) {
        def classes = [String,List,File]
        for(clazz in classes){
            println clazz.package.name
        }

        println( [String,List,File]*.package*.name )
    }
}
