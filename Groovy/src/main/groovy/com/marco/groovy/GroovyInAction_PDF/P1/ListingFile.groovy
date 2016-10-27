package com.marco.groovy.GroovyInAction_PDF.P1

/**
 * Created by marco on 16/9/30.
 */
class ListingFile {

    public static void main(String[] args) {
        def number = 0;
        new File('/Users/marco/IdeaProjects/GrowingPains/Groovy/src/main/resources/data.txt').eachLine { line ->
            number++;
            println "$number: $line"
        }
    }

}
