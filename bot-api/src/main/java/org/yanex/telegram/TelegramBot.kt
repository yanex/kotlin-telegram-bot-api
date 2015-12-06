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
}