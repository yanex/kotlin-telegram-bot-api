package org.yanex.telegram.handler

import org.yanex.telegram.Response
import org.yanex.telegram.entities.Update

interface UpdateHandler {
    
    fun handleUpdate(update: Update)

    /**
     * Handle an error response.
     *
     * @return False if should stop querying for updates, true to continue processing.
     */
    fun onError(response: Response<*>): Boolean
}