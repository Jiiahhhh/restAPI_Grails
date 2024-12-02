package com.ilal

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class BookServiceSpec extends Specification implements ServiceUnitTest<BookService> {

     void "test something"() {
        expect:
        service.doSomething()
     }
}
