package com.example.kotlin.tour.controller

import com.example.kotlin.tour.model.PromocaoDomain
import com.example.kotlin.tour.service.PromocaoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(value = ["/promocoes"])
class PromocaoController(val promocaoService: PromocaoService) {

    @GetMapping
    fun getAll(@RequestParam(required = false, defaultValue = "0") offSet: Int,
               @RequestParam(required = false, defaultValue = "3") limit: Int)
    : ResponseEntity<List<PromocaoDomain>> {
        val listaPromocoes = promocaoService.getAll(offSet, limit)
        val status = if(listaPromocoes.isEmpty()) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(listaPromocoes, status)
    }

    @PostMapping
    fun create(@RequestBody promocaoDomain: PromocaoDomain): ResponseEntity<Any> {
        promocaoService.create(promocaoDomain)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @GetMapping(value = ["/{id}"])
    fun getById(@PathVariable id:Long): ResponseEntity<PromocaoDomain?> {
        val promocao = promocaoService.getById(id)
        return ResponseEntity(promocao, HttpStatus.OK)
    }

    @PutMapping(value = ["/{id}"])
    fun updateById(@PathVariable id:Long, @RequestBody promocaoDomain:PromocaoDomain): ResponseEntity<Any> {
        promocaoService.updateById(id, promocaoDomain)
        return ResponseEntity(HttpStatus.ACCEPTED)
    }

    @DeleteMapping(value = ["/{id}"])
    fun deleteById(@PathVariable id:Long): ResponseEntity<Any> {
        promocaoService.deleteByid(id)
        return ResponseEntity(HttpStatus.ACCEPTED)
    }

}