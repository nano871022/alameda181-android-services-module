package co.com.japl.services.implement

import android.content.ContentValues
import android.provider.BaseColumns
import android.util.Log
import co.com.japl.db.DbHelper
import co.com.japl.db.model.MessageContract
import co.com.japl.interfaces.services.IMessage

import co.japl.android.homeconnect.model.models.Message
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.inject.Inject


class MessageLocalPort  @Inject constructor(private val svc:DbHelper?): IMessage{

   override fun getMessages():List<Message>{
        svc?.let {
            val db = it.readableDatabase
            val projection = arrayOf(
                BaseColumns._ID,
                MessageContract.MessageEntry.COLUMN_MESSAGE,
                MessageContract.MessageEntry.COLUMN_DATE
            )
            val cursor = db.query(
                MessageContract.MessageEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
            )
            val messages = mutableListOf<Message>()
            with(cursor) {
                while (moveToNext()) {
                    val id = getInt(getColumnIndexOrThrow(BaseColumns._ID))
                    val message =
                        getString(getColumnIndexOrThrow(MessageContract.MessageEntry.COLUMN_MESSAGE))
                    val date =
                        getLong(getColumnIndexOrThrow(MessageContract.MessageEntry.COLUMN_DATE))
                    messages.add(Message(id, message, LocalDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneOffset.UTC)))
                }
            }
            return messages.map {Message(it.id, it.message, it.date)}
        }
        return listOf()
    }


    override fun addMessage(message:Message):Boolean{
        svc?.let {
            val db = it.writableDatabase
            val values = ContentValues().apply {
                put(MessageContract.MessageEntry.COLUMN_MESSAGE, message.message)
                put(MessageContract.MessageEntry.COLUMN_DATE, message.date?.toInstant(ZoneOffset.UTC)?.toEpochMilli())
            }
            return (db.insert(MessageContract.MessageEntry.TABLE_NAME, null, values) > 0).also {
                Log.d(this.javaClass.name,"addMessage: added id: $it")
            }
        }
        return false
    }
}