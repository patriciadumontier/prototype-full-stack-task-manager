package org.example.ktor.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object Invoices : Table("invoice") {
    val invoiceNumber = integer("invoice_number") // Primary key not auto-incremented per DDL
    val invoiceDate = date("invoice_date")          // NOT NULL
    val poNumber = varchar("po_number", 50).nullable()
    val customerId = integer("customer_id").references(Customers.id)
    val jobDescription = text("job_description").nullable()
    val invoiceSubtotal = decimal("invoice_subtotal", 12, 2).nullable()
    val invoiceGstAmount = decimal("invoice_gst_amount", 12, 2).nullable()
    val invoiceTotal = decimal("invoice_total", 12, 2).nullable()
    override val primaryKey = PrimaryKey(invoiceNumber, name = "PK_Invoice")
}