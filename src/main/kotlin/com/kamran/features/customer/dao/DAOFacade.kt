package com.kamran.features.customer.dao

import com.kamran.features.customer.models.Customer

interface DAOFacade {
    suspend fun allCustomers(): List<Customer>
    suspend fun addNewCustomer(firstName: String, lastName: String, email: String): Customer?
    /*suspend fun article(id: Int): Customer?
    suspend fun editArticle(id: Int, title: String, body: String): Boolean
    suspend fun deleteArticle(id: Int): Boolean*/
}