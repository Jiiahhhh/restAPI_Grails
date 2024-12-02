package com.ilal

import grails.gorm.transactions.Transactional
import grails.rest.RestfulController

class BookRestController extends RestfulController {
    static responseFormats = ['json', 'xml']

    BookRestController() {
        super(Book)
    }

    @Transactional
    def save() {
        def bookData = request.JSON
        if (!bookData.title || !bookData.author || !bookData.year) {
            respond([message: "Missing required field: title, author, or year"], status: 400)
            return
        }

        def book = new Book(bookData)

        if (book.validate()) {
            if (book.save(flush: true)) {
                respond(book, status: 201) // HTTP 201 Created
            } else {
                respond([message: 'Failed to save book'], status: 400) // HTTP 400 Bad Request
            }
        } else {
            respond([message: 'Validation failed', errors: book.errors], status: 422) // HTTP 422 Unprocessable entity
        }
    }

    @Transactional
    def update(Long id) {
        def book = Book.get(id)
        if (!book) {
            respond([message: 'Book not found'], status: 404)
            return
        }

        def bookData = request.JSON
        book.properties = bookData
        if (book.validate()) {
            if (book.save(flush: true)) {
                respond(book, status: 200)
            } else {
                respond([message: 'Failed to update book'], status: 400)
            }
        } else {
            respond([message: 'Validation failed', errors: book.errors], status: 422)
        }
    }

    @Transactional
    def delete(Long id) {
        def book = Book.get(id)
        if (!book) {
            respond([message: 'Book not found'], status: 404)
            return
        }

        book.delete(flush: true)
        respond([message: 'Book deleted successfully'], status: 200)
    }
}
