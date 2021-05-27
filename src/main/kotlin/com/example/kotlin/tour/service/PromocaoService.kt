package com.example.kotlin.tour.service

import com.example.kotlin.tour.exception.PromocaoNotFoundException
import com.example.kotlin.tour.model.PromocaoDomain
import com.example.kotlin.tour.repository.PromocaoRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PromocaoService(val promocaoRepository: PromocaoRepository) {

    fun getById(id:Long): PromocaoDomain {
        return promocaoRepository.findById(id).orElseThrow { PromocaoNotFoundException("Promoção ${id} não localizada!") }
    }

    fun deleteByid(id:Long) {
        promocaoRepository.findById(id).orElseThrow { PromocaoNotFoundException("Promoção ${id} não localizada!") }
        promocaoRepository.deleteById(id)
    }

    fun updateById(id: Long, promocaoDomain: PromocaoDomain) {
        promocaoRepository.findById(id).orElseThrow { PromocaoNotFoundException("Promoção ${id} não localizada!") }
        deleteByid(id)
        create(promocaoDomain)
    }

    fun create(promocaoDomain: PromocaoDomain) {
        promocaoRepository.save(promocaoDomain)
    }

    fun getAll(offSet: Int, limit: Int): List<PromocaoDomain> {
        val pages:Pageable = PageRequest.of(offSet, limit)
        return promocaoRepository.findAll(pages).toList()
    }
}