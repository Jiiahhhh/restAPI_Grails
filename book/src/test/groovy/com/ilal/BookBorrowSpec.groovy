package com.ilal

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class BookBorrowSpec extends Specification implements DomainUnitTest<BookBorrow> {

     void "test domain constraints"() {
        when:
        BookBorrow domain = new BookBorrow()
        //TODO: Set domain props here

        then:
        domain.validate()
     }
}
