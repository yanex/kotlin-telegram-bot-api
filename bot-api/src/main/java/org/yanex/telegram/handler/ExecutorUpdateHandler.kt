package org.yanex.telegram.handler

import org.yanex.telegram.Response
import org.yanex.telegram.entities.Update
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class ExecutorUpdateHandler(
        private val delegate: UpdateHandler,
        private val executor: Executor = Executors.newCachedThreadPool()
) : UpdateHandler {
    override fun handleUpdate(update: Update) = executor.execute { delegate.handleUpdate(update) }
    override fun onError(response: Response<*>) = delegate.onError(response)
}