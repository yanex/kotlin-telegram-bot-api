package org.yanex.telegram

import org.yanex.telegram.handler.UpdateHandler
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

    fun listen(maxId: Long, handler: UpdateHandler): Long {
        var currentMaxId: Long = maxId
        while (true) {
            val response = getUpdates(offset = currentMaxId)
            val updates = response.result ?: if (handler.onError(response)) continue else break
            for (update in updates) {
                handler.handleUpdate(update)
                currentMaxId = update.updateId + 1
            }
        }
        return currentMaxId
    }
}