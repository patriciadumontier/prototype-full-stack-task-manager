package org.example.ktor.tables.lookup

import org.jetbrains.exposed.sql.Table

object EquipmentCategories : Table("equipment_category") {
    val id = integer("equipment_category_id").autoIncrement()
    val code = varchar("code", 50).uniqueIndex() // UNIQUE and NOT NULL
    override val primaryKey = PrimaryKey(id, name = "PK_EquipmentCategory")
}