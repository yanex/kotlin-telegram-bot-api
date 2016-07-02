package org.yanex.telegram.handler

import org.yanex.telegram.Response
import org.yanex.telegram.entities.*

interface UpdateVisitor {
    fun visitCommand(update: Update, message: Message, command: String, args: String) = visitUpdate(update)
    fun visitText(update: Update, message: Message, text: String) = visitUpdate(update)
    fun visitAudio(update: Update, message: Message, audio: Audio) = visitUpdate(update)
    fun visitPhoto(update: Update, message: Message, photo: List<PhotoSize>, caption: String?) = visitUpdate(update)
    fun visitDocument(update: Update, message: Message, document: Document) = visitUpdate(update)
    fun visitSticker(update: Update, message: Message, sticker: Sticker) = visitUpdate(update)
    fun visitVideo(update: Update, message: Message, video: Video, caption: String?) = visitUpdate(update)
    fun visitContact(update: Update, message: Message, contact: Contact) = visitUpdate(update)
    fun visitLocation(update: Update, message: Message, location: Location) = visitUpdate(update)
    fun visitVenue(update: Update, message: Message, venue: Venue) = visitUpdate(update)
    
    fun visitEdited(update: Update, message: Message) = visitUpdate(update)
    fun visitInlineQuery(update: Update, query: InlineQuery) = visitUpdate(update)
    fun visitChosenInlineResult(update: Update, result: ChosenInlineResult) = visitUpdate(update)
    fun visitCallbackQuery(update: Update, callbackQuery: CallbackQuery) = visitUpdate(update)

    /**
     * Handle an error response.
     * 
     * @return False if should stop querying for updates, true to continue processing.
     */
    fun onError(response: Response<*>): Boolean = true

    /**
     * Process the update that wasn't processed earlier 
     *     (for example, in [visitText] or [visitEdited]).
     * It is the final point after that the update is discarded.
     */
    fun visitUpdate(update: Update) {}
}