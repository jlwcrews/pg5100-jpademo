package no.jlwcrews.jpademo.address

import org.springframework.data.jpa.repository.JpaRepository

interface AddressRepo: JpaRepository<Address, Long> {

    override fun findAll(): MutableList<Address>
}