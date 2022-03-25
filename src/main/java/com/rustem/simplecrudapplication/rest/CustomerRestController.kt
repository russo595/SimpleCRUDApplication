package com.rustem.simplecrudapplication.rest

import com.rustem.simplecrudapplication.model.Customer
import com.rustem.simplecrudapplication.service.CustomerService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api/v1/customers/")
class CustomerRestController(private val customerService: CustomerService) {

    @GetMapping(value = ["{id:\\d+}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getCustomer(@PathVariable("id") customerId: @Size(min = 0) Long?): ResponseEntity<Customer> {
        if (customerId == null) {
            return ResponseEntity(HttpStatus.BAD_GATEWAY)
        }
        val customer = customerService.getById(customerId) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity(customer, HttpStatus.OK)
    }

    @PostMapping(value = [""], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveCustomer(@RequestBody customer: @Valid Customer?): ResponseEntity<Customer> {
        val headers = HttpHeaders()
        if (customer == null) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        customerService.save(customer)
        return ResponseEntity(customer, headers, HttpStatus.CREATED)
    }

    @PutMapping(value = [""], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateCustomer(@RequestBody customer: @Valid Customer?): ResponseEntity<Customer> {
        val headers = HttpHeaders()
        if (customer == null) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        customerService.update(customer)
        return ResponseEntity(customer, headers, HttpStatus.OK)
    }

    @DeleteMapping(value = ["{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteCustomer(@PathVariable("id") id: Long): ResponseEntity<Customer> {
        val customer = customerService.getById(id) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        customerService.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @get:GetMapping(value = [""], produces = [MediaType.APPLICATION_JSON_VALUE])
    val allCustomers: ResponseEntity<MutableList<Customer?>>
        get() {
            val customers = customerService.getAll()
            if (customers.isEmpty()) {
                ResponseEntity<Any>(HttpStatus.NOT_FOUND)
            }
            return ResponseEntity(customers, HttpStatus.OK)
        }
}