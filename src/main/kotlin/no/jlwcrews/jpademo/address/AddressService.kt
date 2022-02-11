package no.jlwcrews.jpademo.address

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AddressService(
    @Autowired private val addressRepo: AddressRepo
) {

    fun createAddressForCustomer(address: Address): Address{
        return addressRepo.save(address)
    }

    fun getAddresses(): List<Address>?{
        return addressRepo.findAll()
    }
}