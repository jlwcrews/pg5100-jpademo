package no.jlwcrews.jpademo.address

import com.fasterxml.jackson.annotation.JsonIgnore
import no.jlwcrews.jpademo.customer.Customer
import javax.persistence.*

enum class AddressType {
    BILLING,
    SHIPPING
}

@Entity
class Address(

    @Id
    @GeneratedValue
    @Column(name = "address_id")
    val id: Long,

    @Column(name = "address_street")
    val street: String,

    @Column(name = "address_city")
    val city: String,

    @Column(name = "address_postcode")
    val postcode: String,

    @Column(name = "address_country")
    val country: String,

    @Column(name = "address_type")
    val type: AddressType,

    @Column(name = "customer_id")
    val customerId: Long,

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    val customer: Customer?
) {
    override fun toString(): String {
        return "Address(id=$id, street='$street', city='$city', postcode='$postcode', country='$country', type=$type)"
    }
}