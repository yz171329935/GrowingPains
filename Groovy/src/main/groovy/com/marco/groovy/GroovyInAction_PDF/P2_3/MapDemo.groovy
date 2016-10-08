package com.marco.groovy.GroovyInAction_PDF.P2_3

/**
 * Created by marco on 16/10/8.
 */
class MapDemo {
    public static void main(String[] args) {
        def http = [
                100 : 'CONTINUE',
                200 : 'OK',
                400 : 'BAD REQUEST'
        ]
        assert http[200] == 'OK'
        http[500] = 'INTERNAL SERVER ERROR'
        assert http.size() == 4

    }
}
