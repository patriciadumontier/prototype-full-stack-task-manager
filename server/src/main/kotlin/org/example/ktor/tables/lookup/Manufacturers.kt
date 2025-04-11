package org.example.ktor.tables.lookup

import org.jetbrains.exposed.sql.Table

object Manufacturers : Table("manufacturer") {
    val id = integer("manufacturer_id").autoIncrement()
    val name = varchar("name", 255).uniqueIndex() // UNIQUE and NOT NULL
    override val primaryKey = PrimaryKey(id, name = "PK_Manufacturer")
}