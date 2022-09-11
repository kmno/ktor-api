package com.kamran.features.customer.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Customer(val id: Int, val firstName: String, val lastName: String, val email: String)

val customerStorage = mutableListOf<Customer>()

object CustomersTable : Table() {
    val id = integer("id").autoIncrement()
    val firstName = varchar("firstName", 255)
    val lastName = varchar("lastName", 255)
    val email = varchar("email", 255)

    override val primaryKey = PrimaryKey(id)
}