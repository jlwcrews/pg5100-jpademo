package no.jlwcrews.jpademo.address

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AddressController(@Autowired private val addressService: AddressService) {

    val logger:Logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping("/address")
    fun createCustomerAddress(@RequestBody address: Address): Address? {
        logger.info(address.toString())
        return addressService.createAddressForCustomer(address)
    }

    @GetMapping("/address")
    fun getAddresses(): List<Address>?{
        return addressService.getAddresses()
    }

}