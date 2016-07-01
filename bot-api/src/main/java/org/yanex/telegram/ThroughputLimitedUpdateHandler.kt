package org.yanex.telegram

import org.yanex.telegram.entities.Message

abstract class ThroughputLimitedUpdateHandler(
        private val delegate: UpdateHandler,
        private val minimalDelayMs: Long
) : UpdateHandler by delegate {
    private var lastUpdateTimeMs: ThreadLocal<Long> = ThreadLocal()

    open fun messageDiscarded(message: Message) {}

    override fun handleMessage(message: Message) {
        val newTimeMs = System.currentTimeMillis()
        val oldTimeMs = lastUpdateTimeMs.get()
        if ((newTimeMs - oldTimeMs) < minimalDelayMs) {
            messageDiscarded(message)
        } else {
            super.handleMessage(message)
        }
        lastUpdateTimeMs.set(newTimeMs)
    }
}