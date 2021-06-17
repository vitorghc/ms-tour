package com.example.kotlin.tour.exception

import com.example.kotlin.tour.model.ErrorResponse
import org.springframework.http.HttpStatus

class UnprocessableEntityException : RestException {

    private var responseBodyCode: String? = null

    private var responseBody: ErrorResponse? = null

    private var arguments: Array<Any>? = null

    override fun getStatus(): HttpStatus {
       return HttpStatus.UNPROCESSABLE_ENTITY
    }

    constructor(responseBodyCode: String) {
        this.responseBodyCode = responseBodyCode
    }

    constructor(responseBody: ErrorResponse) {
        this.responseBody = responseBody
    }

    constructor(responseBodyCode: String, vararg arguments: Any) {
        this.responseBodyCode = responseBodyCode
        this.arguments = arrayOf(*arguments)
    }

    override fun getResponseBodyCode() : String? {
        return responseBodyCode
    }

    override fun getResponseBody() : ErrorResponse? {
        return responseBody
    }

    override fun getArguments() : Array<Any>? {
        return arguments
    }
}