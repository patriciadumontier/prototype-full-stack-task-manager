package org.example.ktor.tables

import org.jetbrains.exposed.sql.Table

object Customers : Table("customer") {
    val id = integer("customer_id").autoIncrement()
    val customerName = varchar("customer_name", 255) // NOT NULL
    val address = text("address").nullable()
    val phone = varchar("phone", 50).nullable()
    override val primaryKey = PrimaryKey(id, name = "PK_Customer")
    init {
        uniqueIndex(customerName, address, phone)
    }
}
