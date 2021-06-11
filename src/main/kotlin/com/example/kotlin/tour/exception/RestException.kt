package com.example.kotlin.tour.exception

import com.example.kotlin.tour.model.ErrorResponse
import org.springframework.http.HttpStatus

abstract class RestException : RuntimeException {

    abstract fun getStatus(): HttpStatus

    constructor() : super() {
    }

    constructor(message: String) : super(message) {
    }

    open fun getResponseBodyCode() : String? {
        return null
    }

    open fun getResponseBody() : ErrorResponse? {
        return null
    }

    open fun getArguments() : Array<Any>? {
        return null
    }

}