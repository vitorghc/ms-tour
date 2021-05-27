package com.example.kotlin.tour.repository

import com.example.kotlin.tour.model.PromocaoDomain
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PromocaoRepository: JpaRepository<PromocaoDomain, Long>{
}