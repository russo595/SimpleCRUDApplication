package com.rustem.simplecrudapplication.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigInteger
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@JsonIgnoreProperties("hibernateLazyInitializer", "handler")
@Table(name = "customers")
class Customer : BaseEntity() {
    @Column(name = "first_name")
    val firstName: String? = null

    @Column(name = "last_name")
    val lastName: String? = null

    @Column(name = "address")
    val address: String? = null

    @Column(name = "budget")
    val budget: BigInteger? = null
}