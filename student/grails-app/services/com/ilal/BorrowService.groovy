package com.ilal

import grails.gorm.transactions.Transactional
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

import java.text.SimpleDateFormat

class BorrowService {

    def bookApiUrl = "http://localhost:8081/api/borrow"

    @Transactional
    def borrowBook(Map params) {
        def bookId = params.bookId
        def name = params.name
        def address = params.address
        def email = params.email
        def borrowDate = params.startDate ? new SimpleDateFormat("yyyy-MM-dd").parse(params.startDate) : null

        if (!bookId || !name || !email || !borrowDate) {
            return [success: false, message: 'Data tidak lengkap']
        }

        def book = getBookFromApi(bookId)
        if (!book) {
            return [success: false, message: "Buku tidak ditemukan"]
        }

        Date returnDate = calculateReturnDate(borrowDate)

        def borrowResponse = sendBorrowRequest(bookId, name, email, address, borrowDate, returnDate)

        if (borrowResponse.success) {
            updateBookQuantity(bookId, book.quantity - 1)
            return [success: true, borrow: borrowResponse.borrow]
        } else {
            return [success: false, message: borrowResponse.message]
        }
    }

    String getBookTitle(Long bookId){
        try {
            String apiUrlBook = "http://localhost:8081/api/books/${bookId}"
            def response = new URL(apiUrlBook).text
            def book = new JsonSlurper().parseText(response)
            return book.title
        } catch (Exception e){
            return "Buku tidak ditemukan: ${e.message}"
        }
    }

    private Date calculateReturnDate(Date borrowDate) {
        Calendar calendar = Calendar.getInstance()
        calendar.setTime(borrowDate)
        calendar.add(Calendar.DAY_OF_MONTH, 7)
        return calendar.getTime()
    }

    private def getBookFromApi(Long bookId) {
        try {
            def apiUrl = "http://localhost:8081/api/books/${bookId}"
            def connection = new URL(apiUrl).openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.connect()

            if (connection.responseCode == 200) {
                def response = connection.inputStream.text
                return new JsonSlurper().parseText(response)
            } else {
                return null
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error mengambil data buku: ${e.message}")
        }
    }

    private def updateBookQuantity(Long bookId, int newQuantity) {
        try {
            def apiUrl = "http://localhost:8081/api/books/${bookId}"
            def connection = new URL(apiUrl).openConnection() as HttpURLConnection
            connection.requestMethod = "PUT"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.doOutput = true

            def updatedBook = '{"quantity": ' + newQuantity + '}'
            connection.outputStream.write(updatedBook.getBytes("UTF-8"))
            connection.connect()

            if (connection.responseCode != 200) {
                throw new IllegalArgumentException("Gagal mengupdate jumlah buku")
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Gagal mengupdate jumlah buku: ${e.message}")
        }
    }

    private def sendBorrowRequest(Long bookId, String name, String email, String address, Date borrowDate, Date returnDate) {
        try {
            def apiurl = bookApiUrl
            def connection = new URL(apiurl).openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.doOutput = true

            def borrowData = [
                    book      : bookId,
                    name      : name,
                    email     : email,
                    address   : address,
                    dateBorrow: new SimpleDateFormat("yyyy-MM-dd").format(borrowDate),
                    dateReturn: new SimpleDateFormat("yyyy-MM-dd").format(returnDate)
            ]

            def jsonPayload = new JsonBuilder(borrowData).toString()
            connection.outputStream.write(jsonPayload.getBytes("UTF-8"))
            connection.connect()

            if (connection.responseCode == 201) {
                def response = connection.inputStream.text
                def borrow = new JsonSlurper().parseText(response)
                return [success: true, borrow: borrow]
            } else {
                def errorResponse = connection.inputStream.text
                return [success: false, message: "Gagal meminjam buku: ${errorResponse}"]
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error mengirim data peminjaman: ${e.message}")
        }
    }
}