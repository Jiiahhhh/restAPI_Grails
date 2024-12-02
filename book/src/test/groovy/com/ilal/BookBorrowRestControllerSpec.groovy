package com.ilal

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class BookBorrowRestControllerSpec extends Specification implements ControllerUnitTest<BookBorrowRestController> {

     void "test index action"() {
        when:
        controller.index()

        then:
        status == 200

     }
}
