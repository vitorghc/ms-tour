package com.example.kotlin.tour.exception

import org.springframework.http.HttpStatus

class NotFoundException(message: String) : RestException(message) {

    override fun getStatus(): HttpStatus {
       return HttpStatus.NOT_FOUND
    }

}