package com.rustem.simplecrudapplication

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SimpleCRUDApplication

fun main(args: Array<String>) {
    runApplication<SimpleCRUDApplication>(*args)
}