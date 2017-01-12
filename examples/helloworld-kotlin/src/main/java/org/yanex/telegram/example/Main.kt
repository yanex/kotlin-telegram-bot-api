package org.yanex.telegram.example

import okhttp3.logging.HttpLoggingInterceptor
import org.yanex.telegram.TelegramBot
import org.yanex.telegram.entities.*
import org.yanex.telegram.handler.*

fun main(args: Array<String>) {
    val properties = TelegramProperties()
    val bot = TelegramBot.create(properties.token, logLevel = HttpLoggingInterceptor.Level.HEADERS)

    val lastId = bot.listen(properties.lastId, VisitorUpdateHandler(object : AbstractUpdateVisitor() {
        override fun visitText(update: Update, message: Message, text: String) = when (text) {
            "ping" -> { sendText(update, "pong"); true }
            "exit" -> throw StopProcessingException()
            else -> false
        }

        override fun visitUpdate(update: Update) = sendText(update, "Unknown command. Try 'ping'.")

        private fun sendText(update: Update, text: String) {
            bot.sendMessage(update.senderId, text)
        }
    }))
    properties.lastId = lastId
}
