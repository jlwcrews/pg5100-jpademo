package no.jlwcrews.jpademo.customer

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class CustomerController(@Autowired private val customerService: CustomerService) {

    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/customers")
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

    @PostMapping("/customer/create")
    fun createCustomer(@RequestBody customer: Customer): Customer? {
        return customerService.createCustomer(customer)
    }

    @PutMapping("/customer/update")
    fun updateCustomer(@RequestBody customer: Customer?): Customer? {
        customer?.let { c ->
             customerService.updateCustomer(c)?.let {
                 return it
             }
        }
        throw CustomerNotFound()
    }

    @DeleteMapping("/customer/delete")
    fun deleteCustomer(@RequestParam customerId: Long?): Boolean {
        customerId?.let {
            return customerService.deleteCustomer(it)
        }
        return false
    }
}

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Customer not found")
class CustomerNotFound: RuntimeException()