package no.jlwcrews.jpademo.address

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class AddressController(@Autowired private val addressService: AddressService) {

    val logger:Logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping("/address")
    fun createCustomerAddress(@RequestBody address: Address): Address? {
        logger.info(address.toString())
        return addressService.createAddressForCustomer(address)
    }

    @GetMapping("/addresses")
    fun getAddresses(): List<Address>?{
        return addressService.getAddresses()
    }

}