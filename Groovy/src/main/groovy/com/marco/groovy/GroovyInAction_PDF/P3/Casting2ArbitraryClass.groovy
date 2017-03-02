package com.marco.groovy.GroovyInAction_PDF.P3

import java.awt.*

/**
 * Created by marco on 16/10/31.
 */
class Casting2ArbitraryClass {

    public static void main(String[] args) {

        Point topLeft = new Point(0, 0) // classic
        Point botRight = [100, 100] // List cast
        Point center = [x:50, y:50] // Map cast
        assert botRight instanceof Point
        assert center instanceof Point
        def rect = new Rectangle()
        rect.location = [0, 0] // Point
        rect.size = [width:100, height:100] // Dimension


    }
}
