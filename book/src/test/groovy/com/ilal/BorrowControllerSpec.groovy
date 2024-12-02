package com.ilal

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class BorrowControllerSpec extends Specification implements ControllerUnitTest<BorrowController> {

     void "test index action"() {
        when:
        controller.index()

        then:
        status == 200

     }
}
