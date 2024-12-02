package com.ilal

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class BorrowSpec extends Specification implements DomainUnitTest<Borrow> {

     void "test domain constraints"() {
        when:
        Borrow domain = new Borrow()
        //TODO: Set domain props here

        then:
        domain.validate()
     }
}
