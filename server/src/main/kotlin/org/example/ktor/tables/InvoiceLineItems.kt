package org.example.ktor.tables

import org.example.ktor.tables.enums.Phase
import org.example.ktor.tables.enums.PowerUnit
import org.example.ktor.tables.enums.TaxCode
import org.example.ktor.tables.lookup.Manufacturers
import org.jetbrains.exposed.sql.Table

object InvoiceLineItems : Table("invoice_line_item") {
    val lineItemId = integer("line_item_id")
        .autoIncrement()
    val invoiceNumber = integer("invoice_number")
        .references(Invoices.invoiceNumber)

    val manufacturerId = integer("manufacturer_id")
        .nullable()
    val equipmentCategoryId = integer("equipment_category_id")
        .nullable()

    val serialNumber = varchar("serial_number", 50)
        .nullable()
    val frame = varchar("frame", 50)
        .nullable()

    // Phase is stored in the DB as values like "1 Phase", "3 Phase", "DC"
    // Use a custom converter to map the DB value to the Phase enum.
    val phase = customEnumeration(
        name = "phase",
        sql = "phase_enum",
        fromDb = { value -> Phase.fromDbValue(value as String) ?: error("Invalid phase: $value") },
        toDb = { phase: Phase -> phase.dbValue }
    ).nullable()

    val equipmentTypeId = integer("equipment_type_id")
        .nullable()
    val power = decimal("power", 10, 2)
        .nullable()

    // For power_unit_enum, DB values "KW" and "HP" match the enum constant names.
    val powerUnit = enumerationByName("power_unit", length = 50, klass = PowerUnit::class)
        .nullable()

    val voltage = varchar("voltage", 50)
        .nullable()
    val rpm = integer("rpm")
        .nullable()
    val quantity = integer("quantity")
        .nullable()
    val lineItemDescription = text("line_item_description")
        .nullable()

    // For tax_code_enum, DB values "G", "H", "E" match the enum constant names.
    val taxCode = enumerationByName("tax_code", length = 50, klass = TaxCode::class)
        .nullable()

    val unitPrice = decimal("unit_price", 10, 2)
        .nullable()
    val amount = decimal("amount", 12, 2)
        .nullable()
    val repairTypeId = integer("repair_type_id")
        .nullable()

    override val primaryKey = PrimaryKey(lineItemId, name = "PK_InvoiceLineItem")
}