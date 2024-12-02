package com.ilal

class Borrow {
    String name
    String address
    String email
    Date dateBorrow
    Date dateReturn

    static constraints = {
        name nullable: false
        address nullable: false
        email nullable: false, unique: true
        dateBorrow blank: false
        dateReturn blank: false
    }
}