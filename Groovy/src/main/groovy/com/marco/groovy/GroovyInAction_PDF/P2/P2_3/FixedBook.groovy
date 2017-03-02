package com.marco.groovy.GroovyInAction_PDF.P2.P2_3

import groovy.transform.Immutable

/**
 * Created by marco on 16/10/8.
 */
@Immutable
class FixedBook {

    String title
}


class FixedBook_Main{

    public static void main(String[] args) {

        def gina = new  FixedBook('Groovy in Action')
        def regina = new  FixedBook(title:'Groovy in Action')

        assert gina.title == 'Groovy in Action'
        assert regina.title == gina.title

        try {
            gina.title = '11111'
            assert false, 'should not reach here'
        }catch (ReadOnlyPropertyException expected){
            println "Expected Error: '$expected.message'"
        }
    }
}
