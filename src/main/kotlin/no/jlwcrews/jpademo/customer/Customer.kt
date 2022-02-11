package no.jlwcrews.jpademo.customer

import no.jlwcrews.jpademo.address.Address
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "Customer")
class Customer(
    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    val customerId: Long,
    @Column(name = "customer_name")
    val name: String,
    @Column(name = "customer_email")
    val email: String,
    @Column(name = "customer_created")
    val created: LocalDateTime? = LocalDateTime.now()
) {
    @OneToMany
    @JoinColumn(name = "customer_id")
    val addresses: MutableSet<Address> = mutableSetOf()

    override fun toString(): String {
        return "Customer(customerId=$customerId, name='$name', email='$email', created=$created, addresses=$addresses)"
    }


}