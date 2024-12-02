package com.ilal

import grails.gorm.transactions.Transactional
import grails.rest.RestfulController

class BookBorrowRestController extends RestfulController {
    static responseFormats = ['json', 'xml']

    BookBorrowRestController() {
        super(BookBorrow)
    }

    @Transactional
    def borrow() {
        def borrowData = request.JSON
        def book = Book.get(borrowData.bookId)

        if (!book) {
            respond([message: 'Buku tidak ditemukan'], status: 404)
            return
        }

        if (book.quantity <= 0) {
            respond([message: 'Nama dan Email kosong'], status: 404)
            return
        }

        def borrow = new BookBorrow(
                book: book,
                name: borrowData.name,
                email: borrowData.email,
                dateBorrow: new Date(),
                dateReturn: calculateReturnDate(new Date())
        )

        if (borrow.validate()) {
            if (borrow.save(flush: true)) {
                updateBookQuantity(book, book.quantity - 1)
                respond([borrow   : borrow,
                         bookTitle: book.title],
                        status: 201) // HTTP 201 dibuat
            } else {
                respond([message: 'Gagal meminjam buku'], status: 400) //HTTP 400 Bad Request
            }
        } else {
            respond([message: 'Validasi gagal', errors: borrow.errors], status: 422)
        }
    }

    private Date calculateReturnDate(Date borrowDate) {
        Calendar calendar = Calendar.getInstance()
        calendar.setTime(borrowDate)
        calendar.add(Calendar.DAY_OF_MONTH, 7) //kembalikan dalam 7 hari
        return calendar.getTime()
    }

    private void updateBookQuantity(Book book, int newQuantity) {
        book.quantity = newQuantity
        book.save(flush: true)
    }

    def listBorrowBooks() {
        def borrowedBooks = BookBorrow.list()
        respond(borrowedBooks)
    }
}