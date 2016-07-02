package org.yanex.telegram.handler

import org.yanex.telegram.Response
import org.yanex.telegram.entities.Message
import org.yanex.telegram.entities.Update

class VisitorUpdateHandler(val visitor: UpdateVisitor) : UpdateHandler {
    override fun onError(response: Response<*>) = visitor.onError(response)
    override fun handleUpdate(update: Update) = visitor.processUpdate(update)
    
    private fun UpdateVisitor.processUpdate(update: Update) {
        if (update.message != null) {
            handleNewMessage(update, update.message)
        } else if (update.editedMessage != null) {
            visitEdited(update, update.editedMessage)
        } else if (update.inlineQuery != null) {
            visitInlineQuery(update, update.inlineQuery)
        } else if (update.chosenInlineResult != null) {
            visitChosenInlineResult(update, update.chosenInlineResult)
        } else if (update.callbackQuery != null) {
            visitCallbackQuery(update, update.callbackQuery)
        } else {
            visitUpdate(update)
        }
    }

    private fun UpdateVisitor.handleNewMessage(update: Update, message: Message) {
        if (message.text != null) {
            val matcher = COMMAND_REGEXP.matcher(message.text)
            if (matcher.matches()) {
                val command = matcher.group(1)
                val args = matcher.group(3)
                visitCommand(update, message, command, args)
            } else {
                visitText(update, message, message.text)
            }
        } else if (message.audio != null) {
            visitAudio(update, message, message.audio)
        } else if (message.photo != null) {
            visitPhoto(update, message, message.photo, message.caption)
        } else if (message.document != null) {
            visitDocument(update, message, message.document)
        } else if (message.sticker != null) {
            visitSticker(update, message, message.sticker)
        } else if (message.video != null) {
            visitVideo(update, message, message.video, message.caption)
        } else if (message.contact != null) {
            visitContact(update, message, message.contact)
        } else if (message.venue != null) {
            visitVenue(update, message, message.venue)
        } else if (message.location != null) {
            visitLocation(update, message, message.location)
        } else {
            visitUpdate(update)
        }
    }

    private companion object {
        private val COMMAND_REGEXP = "^\\/([A-Za-z0-9_]{1,32})( (.*))?$".toPattern()
    }
}