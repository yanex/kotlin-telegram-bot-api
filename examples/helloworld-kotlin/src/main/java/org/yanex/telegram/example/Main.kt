package org.yanex.telegram.example

import okhttp3.logging.HttpLoggingInterceptor
import org.yanex.telegram.Response
import org.yanex.telegram.TelegramBot
import org.yanex.telegram.entities.*
import org.yanex.telegram.handler.*
import retrofit2.Call
import retrofit2.Callback

fun main(args: Array<String>) {
    val properties = TelegramProperties()
    val bot = TelegramBot.create(properties.token, logLevel = HttpLoggingInterceptor.Level.BODY)

    val lastId = bot.listen(properties.lastId, VisitorUpdateHandler(object : AbstractUpdateVisitor() {
        override fun visitText(update: Update, message: Message, text: String) = when (text) {
            "ping" -> { sendText(update, "pong"); true }
            "exit" -> throw StopProcessingException()
            else -> false
        }

        override fun visitUpdate(update: Update) = sendText(update, "Unknown command. Try 'ping'.")

        private fun sendText(update: Update, text: String) {
            bot.sendMessage(update.senderId, text).enqueue(object : Callback<Response<Message>> {
                override fun onResponse(call: Call<Response<Message>>?, response: retrofit2.Response<Response<Message>>?) {
                }

                override fun onFailure(call: Call<Response<Message>>?, t: Throwable?) {
                }
            })
        }
    }))
    properties.lastId = lastId
}
