package com.kamran.features.customer.dao

import com.kamran.database.DatabaseFactory.dbQuery
import com.kamran.features.customer.models.Customer
import com.kamran.features.customer.models.CustomersTable
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class DAOFacadeImpl : DAOFacade {

    private fun resultRowToArticle(row: ResultRow) = Customer(
        id = row[CustomersTable.id],
        firstName = row[CustomersTable.firstName],
        lastName = row[CustomersTable.lastName],
        email = row[CustomersTable.email]
    )

    override suspend fun allCustomers(): List<Customer> = dbQuery {
        CustomersTable.selectAll().map(::resultRowToArticle)
    }

    override suspend fun addNewCustomer(firstName: String, lastName: String, email: String): Customer? = dbQuery {
        val insertStatement = CustomersTable.insert {
            it[CustomersTable.firstName] = firstName
            it[CustomersTable.lastName] = lastName
            it[CustomersTable.email] = email
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToArticle)
    }
}

val customersDAO: DAOFacade = DAOFacadeImpl().apply {
    runBlocking {
        if(allCustomers().isEmpty()) {
            //addNewArticle("The drive to develop!", "...it's what keeps me going.")
        }
    }
}