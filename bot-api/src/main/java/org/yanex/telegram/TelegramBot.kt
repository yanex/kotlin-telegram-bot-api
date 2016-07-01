package org.yanex.telegram

import org.yanex.telegram.entities.Message
import retrofit.RestAdapter
import retrofit.RestAdapter.LogLevel

class TelegramBot internal constructor(serviceProvider: TelegramBotService) : TelegramBotService by serviceProvider {
    companion object {
        @JvmStatic
        fun create(token: String, logLevel: LogLevel): TelegramBotService {
            val adapter = RestAdapter.Builder()
                    .setEndpoint("https://api.telegram.org/bot$token")
                    .setLogLevel(logLevel)
                    .build()
            return adapter.create(TelegramBotService::class.java)
        }
    }

    fun listen(errorHandler: (Response<*>) -> Boolean, maxId: Long, handler: (Message) -> Unit): Long {
        var currentMaxId: Long = maxId
        while (true) {
            val response = getUpdates(offset = currentMaxId)
            val updates = response.result ?: if (errorHandler(response)) continue else break
            for (update in updates) {
                update.message?.let { handler(it) }
                currentMaxId = update.updateId + 1
            }
        }
        return currentMaxId
    }

    fun listen(handler: UpdateHandler, maxId: Long = 0) = listen({ handler.handleError(it) }, maxId) {
        handler.handleMessage(it)
    }
}