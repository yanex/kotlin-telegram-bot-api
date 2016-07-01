package org.yanex.telegram.entities

import com.google.gson.annotations.SerializedName as Name

/**
 * This object represents an incoming callback query from a callback button in an inline keyboard. 
 * If the button that originated the query was attached to a message sent by the bot, the field message will be presented. 
 * If the button was attached to a message sent via the bot (in inline mode), the field inline_message_id will be presented.
 *
 * @property id Unique identifier for this query.
 * @property from Sender.
 * @property message Message with the callback button that originated the query. 
 *           Note that message content and message date will not be available if the message is too old.
 * @property inlineMessageId Identifier of the message sent via the bot in inline mode, that originated the query.
 * @property data Data associated with the callback button. Be aware that a bad client can send arbitrary data in this field.
 */
data class CallbackQuery(
        val id: String,
        val from: User,
        val message: Message?,
        @Name("inline_message_id") val inlineMessageId: String?,
        val data: String)