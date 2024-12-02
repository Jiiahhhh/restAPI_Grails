package com.ilal

class UrlMappings {
    static mappings = {
        "/api/books"(resources: 'bookRest')
        "/api/borrow"(resources: 'bookBorrowRest')
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
