package co.com.japl.db.model

import android.provider.BaseColumns

object MessageContract {

    const val SQL_CREATE_TABLE = """
        CREATE TABLE ${MessageEntry.TABLE_NAME} (
            ${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT,
            ${MessageEntry.COLUMN_MESSAGE} TEXT NOT NULL,
            ${MessageEntry.COLUMN_DATE} NUMBER NOT NULL
        )
    """
    const val SQL_DELETE_ENTRIRES = "DROP TABLE IF EXISTS ${MessageEntry.TABLE_NAME}"

    val SQL_ALTER_ENTRIES = mapOf<Long,String>()

    object MessageEntry: BaseColumns{
        const val TABLE_NAME = "ur_messages"
        const val COLUMN_MESSAGE = "message"
        const val COLUMN_DATE = "date"
    }
}