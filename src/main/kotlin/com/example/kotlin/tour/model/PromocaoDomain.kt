package com.example.kotlin.tour.model

import javax.persistence.*

@Entity
@Table(name = "TBL_PROMOCAO")
data class PromocaoDomain(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 1,

    @Column(name = "descricao")
    val descricao: String? = "",

    @Column(name = "local")
    val local: String = "",

    @Column(name = "is_all_inclusive")
    val isAllInclusive: Boolean = false,

    @Column(name = "qtd_dias")
    val qtdDias: Int = 1,

    @Column(name = "preco")
    var preco: Double = 0.0
)