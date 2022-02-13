package no.jlwcrews.jpademo.customer

import no.jlwcrews.jpademo.address.Address
import no.jlwcrews.jpademo.address.AddressRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CustomerService(
    @Autowired private val customerRepo: CustomerRepo,
    @Autowired private val addressRepo: AddressRepo) {

    fun createCustomer(customer: Customer): Customer {
        return customerRepo.save(customer)
    }

    fun getCustomer(id: Long): Customer? {
        return customerRepo.findById(id).orElse(null)
    }

    fun getCustomers(): List<Customer>{
        return customerRepo.findAll()
    }

    fun updateCustomer(customerId: Long, customer: Customer): Customer? {
        if(customerRepo.existsById(customer.customerId)){
            return customerRepo.save(customer)
        }
        return null
    }

    fun deleteCustomer(id: Long): Boolean {
        if(customerRepo.existsById(id)){
            customerRepo.deleteById(id)
            return true
        }
        return false
    }

    fun addCustomerAddress(address: Address){
        addressRepo.save(address)
    }

    fun updateCustomerEmail(customerId: Long, email: String): Boolean {

        customerRepo.findById(customerId).orElse(null)?.let {
            val newCustomer = Customer(
                customerId = it.customerId,
                name = it.name,
                email = email,
                created = it.created
                )
            customerRepo.save(newCustomer)
            return true
        }
        return false
    }

}