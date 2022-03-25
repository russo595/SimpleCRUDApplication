package com.rustem.simplecrudapplication.service

import com.rustem.simplecrudapplication.model.Customer
import com.rustem.simplecrudapplication.repository.CustomerRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class CustomerServiceImpl(private val customerRepository: CustomerRepository) : CustomerService {

    @Transactional(readOnly = true)
    override fun getById(id: Long): Customer? {
        log.info("In CustomerServiceImpl getById {}", id)
        val customer: Optional<Customer?> = customerRepository.findById(id)
        return customer.orElse(null)
    }

    override fun save(customer: Customer) {
        log.info("In CustomerServiceImpl save {}", customer)
        customerRepository.save<Customer>(customer)
    }

    override fun update(c: Customer) {
        val customer: Optional<Customer?> = customerRepository.findById(c.id!!)
        var update: Int? = null
        if (customer.isPresent) {
            update =
                customerRepository.update(c.id, c.firstName, c.lastName, c.address, c.budget)
            log.info("Updated: {}", update)
        }
    }

    override fun delete(id: Long) {
        log.info("In CustomerServiceImpl delete {}", id)
        customerRepository.deleteById(id)
    }

    override fun getAll(): MutableList<Customer?> {
        log.info("In CustomerServiceImpl getAll")
        return customerRepository.findAll()
    }

    companion object {
        private val log = LoggerFactory.getLogger(CustomerServiceImpl::class.java)
    }
}