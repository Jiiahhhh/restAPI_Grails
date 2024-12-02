package com.ilal

import grails.gorm.annotation.Entity
import groovy.transform.ToString

@Entity
@ToString(includeNames = true, includeFields = true)
class BookBorrow {
    Book book
    String name
    String email
    String address
    Date dateBorrow
    Date dateReturn

    static constraints = {
        book nullable: false
        name nullable: false
        email nullable: false
        address nullable: false
        dateBorrow nullable: false
        dateReturn nullable: false
    }

    static mapping = {
        table 'book_borrow'
    }
}