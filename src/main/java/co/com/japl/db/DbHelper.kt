package co.com.japl.db

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import co.com.japl.db.model.MessageContract
import javax.inject.Inject

class DbHelper @Inject constructor (context:Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: android.database.sqlite.SQLiteDatabase?) {
        db?.execSQL(MessageContract.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: android.database.sqlite.SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        MessageContract.SQL_ALTER_ENTRIES[newVersion.toLong()]?.let{db?.execSQL(it)}
    }

    companion object{
        const val DATABASE_NAME = "ur_database"
        const val DATABASE_VERSION = 1_00_001
    }
}