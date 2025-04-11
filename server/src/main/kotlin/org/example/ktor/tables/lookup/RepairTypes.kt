package org.example.ktor.tables.lookup

import org.jetbrains.exposed.sql.Table

object RepairTypes : Table("repair_type") {
    val id = integer("repair_type_id").autoIncrement()
    val type = varchar("type", 100).uniqueIndex() // UNIQUE and NOT NULL
    override val primaryKey = PrimaryKey(id, name = "PK_RepairType")
}