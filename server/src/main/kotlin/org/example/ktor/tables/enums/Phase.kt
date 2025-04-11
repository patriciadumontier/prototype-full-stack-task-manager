package org.example.ktor.tables.enums

enum class Phase(val dbValue: String) {
    ONE_PHASE("1 Phase"),
    THREE_PHASE("3 Phase"),
    DC("DC");

    companion object {
        fun fromDbValue(value: String): Phase? =
            entries.find { it.dbValue == value }
    }
}