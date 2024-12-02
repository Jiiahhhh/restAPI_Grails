package com.ilal

class BootStrap {

    def init = { servletContext ->
        (0..10).each { i ->
            def book = new Book(
                    title: "Buku ${i}",
                    author: "Author ${i}",
                    quantity: 10,
                    year: 2010 + i
            )
            book.save(failOnError: true)
        }
    }
    def destroy = {
    }
}