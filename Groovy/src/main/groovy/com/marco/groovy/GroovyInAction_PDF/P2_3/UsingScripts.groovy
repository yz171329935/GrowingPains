package com.marco.groovy.GroovyInAction_PDF.P2_3

/**
 * Created by marco on 16/10/6.
 * Any Groovy code can be executed this way as long as it can be run;
 * that is, it’s either a script, a class with a main method, a Runnable, or a Groovy or JUnit test case.
 */

class UsingScripts {

    public static void main(String[] args) {
        def gina = new Book('Groovy in Action')

        assert gina.getTitle() == 'Groovy in Action'
        assert getTitleBackwards(gina) == 'noitcA ni yvoorG'
        assert getTitleBackwards(gina) == 'noitcA ni yvoorG'


    }
    def static String getTitleBackwards(book) {
        String title = book.getTitle()
        return title.reverse()
    }
}
