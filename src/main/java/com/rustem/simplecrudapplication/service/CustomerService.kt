package com.rustem.simplecrudapplication.service

import com.rustem.simplecrudapplication.model.Customer

interface CustomerService {
    fun getById(id: Long): Customer?
    fun save(customer: Customer)
    fun update(customer: Customer)
    fun delete(id: Long)
    fun getAll(): MutableList<Customer?>
}