package com.ilal

class BookController {
    BookService bookService

    def index() {
        def book = bookService.getBook()
        [bookList: book]
    }

    def create() {}

    def save() {
        def result = bookService.saveBook(params)
        if (result.success) {
            flash.message = "Buku berhasil ditambahkan"
            redirect(action: 'index')
        } else {
            flash.message = "Gagal menyimpan buku"
            render(view: 'create')
        }
    }

    def edit(Long id) {
        def book = Book.get(id)
        if (!book) {
            flash.message = "Buku tidak ditemukan"
            redirect(action: 'index')
        }
        render(view: 'create', model: [book: book])
    }

    def update(Long id) {
        def result = bookService.updateBook(id, params)
        if (result.success) {
            flash.message = "Buku berhasil diperbarui"
            redirect(action: 'index')
        } else {
            flash.message = "Gagal memperbarui buku"
            render(view: 'create', model: [book: result.book])
        }
    }

    def delete(Long id) {
        def result = bookService.deleteBook(id)
        if (result.success) {
            flash.message = "Buku berhasil dihapus"
        } else {
            flash.message = "Gagal menghapus buku"
        }
        redirect(action: 'index')
    }
}
