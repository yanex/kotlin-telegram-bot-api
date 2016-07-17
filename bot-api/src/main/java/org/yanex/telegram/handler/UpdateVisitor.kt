package org.yanex.telegram.handler

import org.yanex.telegram.Response
import org.yanex.telegram.entities.*

interface UpdateVisitor {
    fun visitText(update: Update, message: Message, text: String): Boolean
    fun visitAudio(update: Update, message: Message, audio: Audio): Boolean
    fun visitPhoto(update: Update, message: Message, photo: List<PhotoSize>, caption: String?): Boolean
    fun visitDocument(update: Update, message: Message, document: Document): Boolean
    fun visitSticker(update: Update, message: Message, sticker: Sticker): Boolean
    fun visitVideo(update: Update, message: Message, video: Video, caption: String?): Boolean
    fun visitContact(update: Update, message: Message, contact: Contact): Boolean
    fun visitLocation(update: Update, message: Message, location: Location): Boolean
    fun visitVenue(update: Update, message: Message, venue: Venue): Boolean
    
    fun visitEdited(update: Update, message: Message): Boolean
    fun visitInlineQuery(update: Update, query: InlineQuery): Boolean
    fun visitChosenInlineResult(update: Update, result: ChosenInlineResult): Boolean
    fun visitCallbackQuery(update: Update, callbackQuery: CallbackQuery): Boolean

    /**
     * Handle an error response.
     * 
     * @return False if should stop querying for updates, true to continue processing.
     */
    fun onError(response: Response<*>): Boolean

    /**
     * Process the update that wasn't processed earlier 
     *     (for example, in [visitText] or [visitEdited]).
     * It is the final point after that the update is discarded.
     */
    fun visitUpdate(update: Update)
}

abstract class AbstractUpdateVisitor : UpdateVisitor {
    override fun visitText(update: Update, message: Message, text: String) = false
    override fun visitAudio(update: Update, message: Message, audio: Audio) = false
    override fun visitPhoto(update: Update, message: Message, photo: List<PhotoSize>, caption: String?) = false
    override fun visitDocument(update: Update, message: Message, document: Document) = false
    override fun visitSticker(update: Update, message: Message, sticker: Sticker) = false
    override fun visitVideo(update: Update, message: Message, video: Video, caption: String?) = false
    override fun visitContact(update: Update, message: Message, contact: Contact) = false
    override fun visitLocation(update: Update, message: Message, location: Location) = false
    override fun visitVenue(update: Update, message: Message, venue: Venue) = false

    override fun visitEdited(update: Update, message: Message) = false
    override fun visitInlineQuery(update: Update, query: InlineQuery) = false
    override fun visitChosenInlineResult(update: Update, result: ChosenInlineResult) = false
    override fun visitCallbackQuery(update: Update, callbackQuery: CallbackQuery) = false

    override fun onError(response: Response<*>) = true

    override fun visitUpdate(update: Update) {}
}