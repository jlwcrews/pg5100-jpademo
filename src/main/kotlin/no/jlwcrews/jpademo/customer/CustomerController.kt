package no.jlwcrews.jpademo.customer

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.security.InvalidParameterException

@RestController
class CustomerController(@Autowired private val customerService: CustomerService) {

    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/customer")
    fun getCustomers(): List<Customer>? {
        return customerService.getCustomers()
    }

    @GetMapping("/customer/{customerId}")
    fun getCustomer(@PathVariable customerId: Long?): Customer? {
        customerId?.let {
            val customer = customerService.getCustomer(it)
            if (customer != null) return customer else throw CustomerNotFound()
        }
        throw CustomerNotFound()
    }

    @PostMapping("/customer")
    fun createCustomer(@RequestBody customer: Customer): Customer? {
        return customerService.createCustomer(customer)
    }

    @PutMapping("/customer/{customerId}")
    fun updateCustomer(@PathVariable customerId: Long?, @RequestBody customer: Customer?): Customer? {
        when {
            customerId == null -> throw InvalidParameterException()
            customer == null -> throw InvalidParameterException()
            else -> {
                customerService.updateCustomer(customerId, customer)?.let {
                    return it
                }
                throw CustomerNotFound()
            }
        }
    }

    @PatchMapping("/customer/{customerId}/email")
    fun updateCustomerEmail(@PathVariable customerId: Long?, @RequestBody email: JsonNode){
        customerId?.let {
            if (!customerService.updateCustomerEmail(it, email.get("email").asText())) throw CustomerNotFound()
        }
    }

    @DeleteMapping("/customer/{customerId}/delete")
    fun deleteCustomer(@PathVariable customerId: Long?) {
        customerId?.let {
            if (!customerService.deleteCustomer(it)) throw CustomerNotFound()
        }
    }
}

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Customer not found")
class CustomerNotFound: RuntimeException()