package com.marco.groovy.GroovyInAction_PDF.P2_3

/**
 * Created by marco on 16/10/8.
 * The @Grab annotation is used to explicitly define your external library dependencies within a script.
 * We sometimes use the term grapes as friendly shorthand for our external Groovy library dependencies.
 * In the Java world, you might store your dependent libraries in a lib directory and add that to your classpath and IDE settings,
 * or you might capture that information in an Ivy, Maven, or Gradle build file
 */
//@Grab('commons-lang:commons-lang:2.4')
import org.apache.commons.lang.ClassUtils

class Outer {
    class Inner {}
}

class Outer_Main {

    public static void main(String[] args) {

        assert ClassUtils.isInnerClass(Outer.Inner)
        assert !ClassUtils.isInnerClass(Outer)

    }

}