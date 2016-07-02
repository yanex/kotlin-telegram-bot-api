package org.yanex.telegram.handler

import org.yanex.telegram.Response
import org.yanex.telegram.entities.Update

abstract class ThroughputLimitedUpdateHandler(
        private val delegate: UpdateHandler,
        private val minimalDelayMs: Long
) : UpdateHandler {
    private val lastUpdateTimeMs: ThreadLocal<Long> = ThreadLocal()

    abstract fun onDiscarded(update: Update)
    
    final override fun onError(response: Response<*>) = delegate.onError(response)
    
    final override fun handleUpdate(update: Update) {
        val newTimeMs = System.currentTimeMillis()
        val oldTimeMs = lastUpdateTimeMs.get()
        
        if ((newTimeMs - oldTimeMs) < minimalDelayMs) {
            onDiscarded(update)
        } else {
            delegate.handleUpdate(update)
        }
        
        lastUpdateTimeMs.set(newTimeMs)
    }
}