package com.ilal

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class BookRestControllerSpec extends Specification implements ControllerUnitTest<BookRestController> {

     void "test index action"() {
        when:
        controller.index()

        then:
        status == 200

     }
}
