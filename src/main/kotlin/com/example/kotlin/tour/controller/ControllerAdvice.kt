package com.example.kotlin.tour.controller

import com.example.kotlin.tour.exception.RestException
import com.example.kotlin.tour.model.ErrorResponse
import com.fasterxml.jackson.core.JsonParseException
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@ControllerAdvice
class ControllerAdvice (val messageSource: MessageSource){

    @ExceptionHandler(JsonParseException::class)
    fun jsonParseExceptionHandler(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse, exception: Exception):
            ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse("Error Json", exception.message ?: "Invalid Json"),
            HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(RestException::class)
    fun handleRestException(restException: RestException) : ResponseEntity<Any> {
        if(!restException.getResponseBodyCode().isNullOrBlank()){
            return ResponseEntity.status(restException.getStatus())
                .body(ErrorResponse(restException.getResponseBodyCode().orEmpty(),
                    getMessage(restException.getResponseBodyCode().orEmpty(),
                        restException.getArguments())))
        }
        if(restException.getResponseBody() != null) {
                return ResponseEntity.status(restException.getStatus())
                .body(restException.getResponseBody())
            }
        return ResponseEntity.status(restException.getStatus()).build()
    }

    private fun getMessage(code: String, args: Array<Any>?): String {
        return this.messageSource.getMessage(code, args, LocaleContextHolder.getLocale())
    }

}