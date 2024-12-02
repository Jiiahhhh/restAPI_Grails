package com.ilal

import groovy.json.JsonSlurper

class BookViewerController {

    String apiUrl = 'http://localhost:8081/api/books'
    BorrowService borrowService

    def index() {
        try {
            def response = new URL(apiUrl).text
            def books = new JsonSlurper().parseText(response)
            [bookList: books]
        } catch (Exception e) {
            flash.message = "Gagal mengambil data buku dari API. Penyebab: ${e.message}"
            render(view: 'error')
        }
    }

    def borrow() {
        [bookId: params.id]
    }

    def listBorrow() {
        try {
            String apiUrlBorrow = "http://localhost:8081/api/borrow"
            def response = new URL(apiUrlBorrow).text
            def borrowedBooks = new JsonSlurper().parseText(response)

            def booksWithTitles = borrowedBooks.collect{ borrow ->
                def bookTitle = borrowService.getBookTitle(borrow.book.id)
                borrow.bookTitle = bookTitle
                return borrow
            }
            [bookList: booksWithTitles]
        } catch (Exception e) {
            flash.message = "Gagal mengambil data buku dari API. Penyebab: ${e.message}"
            render(view: 'error')
        }
    }

    // Proses peminjaman buku
    def borrowed() {
        try {
            params.bookId = params.bookId?.toLong()
            def result = borrowService.borrowBook(params)
            if (result.success) {
                flash.message = "Peminjaman buku berhasil"
            } else {
                flash.message = "Peminjaman buku gagal: ${result.message}"
            }
            redirect(action: 'index')
        } catch (IllegalArgumentException e) {
            flash.message = "Error: ${e.message}"
            render(view: 'borrow', model: [bookId: params.bookId])
        } catch (Exception e) {
            flash.message = "Terjadi kesalahan tak terduga: ${e.message}"
            render(view: 'borrow', model: [bookId: params.bookId])
        }
    }
}
