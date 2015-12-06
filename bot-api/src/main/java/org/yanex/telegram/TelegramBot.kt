package org.yanex.telegram

import retrofit.RestAdapter
import retrofit.RestAdapter.LogLevel

class TelegramBot internal constructor(serviceProvider: TelegramBotService) : TelegramBotService by serviceProvider {
    constructor(token: String, logLevel: LogLevel = LogLevel.NONE) : this(create(token, logLevel))

    private companion object {
        fun create(token: String, logLevel: LogLevel): TelegramBotService {
            val adapter = RestAdapter.Builder()
                    .setEndpoint("https://api.telegram.org/bot$token")
                    .setLogLevel(logLevel)
                    .build()
            return adapter.create(TelegramBotService::class.java)
        }
    }

    fun listen(errorHandler: (Response<*>) -> Boolean = { true }, handler: (Message) -> Unit) {
        var maxId: Long? = null
        while (true) {
            val response = getUpdates(offset = maxId)
            val updates = response.result ?: if (errorHandler(response)) continue else return
            for (update in updates) {
                handler(update.message)
                maxId = update.updateId + 1
            }
        }
    }
}