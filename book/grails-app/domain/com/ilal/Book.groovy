package com.ilal

class Book {
    String title
    String author
    Integer quantity
    Integer year

    static constraints = {
        title nullable: false
        author nullable: false
        quantity nullable: false
        year nullable: false
    }
}