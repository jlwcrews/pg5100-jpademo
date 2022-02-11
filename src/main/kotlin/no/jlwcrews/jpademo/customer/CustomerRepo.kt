package no.jlwcrews.jpademo.customer

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepo: JpaRepository<Customer, Long> {

    override fun findAll():List<Customer>
}