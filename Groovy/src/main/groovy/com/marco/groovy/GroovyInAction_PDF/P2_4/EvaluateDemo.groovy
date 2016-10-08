package com.marco.groovy.GroovyInAction_PDF.P2_4

/**
 * Created by marco on 16/10/8.
 */
class EvaluateDemo {

    public static void main(String[] args) {

        def code = '1 + '
        code += System.getProperty('java.class.version')
        assert code == '1 + 51.0'
        assert 52.0 == evaluate(code)

    }
}
