package org.yanex.telegram.entities

import com.google.gson.annotations.SerializedName as Name

/**
 * Represents a result of an inline query that was chosen by the user and sent to their chat partner.

 * @property resultId The unique identifier for the result that was chosen.
 * @property from The user that chose the result.
 * @property location Sender location, only for bots that require user location.
 * @property inlineMessageId Identifier of the sent inline message. 
 *           Available only if there is an inline keyboard attached to the message. 
 *           Will be also received in callback queries and can be used to edit the message.
 * @property query The query that was used to obtain the result.
 */
data class ChosenInlineResult(
        @Name("result_id") val resultId: String,
        val from: User,
        val location: Location?,
        @Name("inline_message_id") val inlineMessageId: String?,
        val query: String)