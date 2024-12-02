package com.ilal


class BorrowController {

    BorrowService borrowService

    def index() {
        def borrow = borrowService.getBorrow()
        [borrowList: borrow]
    }
}