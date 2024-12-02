package com.ilal

import grails.gorm.transactions.Transactional
import groovy.transform.CompileStatic
import org.springframework.validation.BindingResult

@CompileStatic
class BookService {

    def getBook() {
        def book = Book.list()
        return book
    }

    @Transactional
    def saveBook(Map params) {
        def book = new Book(
                title: params.title,
                author: params.author,
                year: params.year as Integer
        )
        if (!book.validate()) {
            return [success: false, message: "Validation failed, error: ${book.errors}"]
        }

        if (book.save(flush: true)) {
            return [success: true, book: book]
        } else {
            return [success: false, message: "Failed to save book."]
        }
    }

    @Transactional
    def updateBook(Long id, Map params) {
        def book = Book.get(id)
        if (!book) {
            return [success: false, message: "Book not found"]
        }

        book.properties = params as BindingResult
        if (!book.validate()) {
            return [success: false, message: "Validation failed", book: book]
        }

        if (book.save(flush: true)) {
            return [success: true, book: book]
        } else {
            return [success: false, message: "Failed to update book.", book: book]
        }
    }

    @Transactional
    def deleteBook(Long id) {
        def book = Book.get(id)
        if (!book) {
            return [success: false, message: "Book not found"]
        }

        book.delete(flush: true)
        return [success: true, message: "Book deleted successfully"]
    }
}