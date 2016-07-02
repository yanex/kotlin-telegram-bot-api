package org.yanex.telegram

import org.yanex.telegram.handler.StopProcessingException
import org.yanex.telegram.handler.UpdateHandler
import retrofit.RestAdapter
import retrofit.RestAdapter.LogLevel

class TelegramBot internal constructor(serviceProvider: TelegramBotService) : TelegramBotService by serviceProvider {
    companion object {
        @JvmStatic
        fun create(token: String, logLevel: LogLevel): TelegramBot {
            val adapter = RestAdapter.Builder()
                    .setEndpoint("https://api.telegram.org/bot$token")
                    .setLogLevel(logLevel)
                    .build()
            return TelegramBot(adapter.create(TelegramBotService::class.java))
        }
    }

    @JvmOverloads
    fun listen(maxId: Long, handler: UpdateHandler, timeout: Int = 30): Long {
        var currentMaxId: Long = maxId
        outer@ while (true) {
            val response = if (currentMaxId > 0) 
                getUpdates(offset = currentMaxId, timeout = timeout)
            else 
                getUpdates(timeout = timeout)
            
            val updates = response.result ?: if (handler.onError(response)) continue else break
            for (update in updates) {
                try {
                    handler.handleUpdate(update)
                } catch (e: StopProcessingException) {
                    currentMaxId = update.updateId + 1
                    break@outer
                }
                currentMaxId = update.updateId + 1
            }
        }
        return currentMaxId
    }
}