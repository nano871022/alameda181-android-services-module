package co.com.japl.interfaces.services

import co.japl.android.homeconnect.model.models.Message

interface IMessage {

    fun getMessages():List<Message>

    fun addMessage(message:Message):Boolean


}