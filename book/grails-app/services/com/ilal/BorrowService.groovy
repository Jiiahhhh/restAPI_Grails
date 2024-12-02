package com.ilal

class BorrowService {

    def getBorrow() {
        return BookBorrow.list()
    }
}