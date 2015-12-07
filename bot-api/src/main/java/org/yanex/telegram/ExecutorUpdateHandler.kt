package org.yanex.telegram

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class ExecutorUpdateHandler(
        private val delegate: UpdateHandler,
        private val executor: Executor = Executors.newCachedThreadPool()
) : UpdateHandler by delegate {
    override fun handleMessage(message: Message) {
        executor.execute { super.handleMessage(message) }
    }
}