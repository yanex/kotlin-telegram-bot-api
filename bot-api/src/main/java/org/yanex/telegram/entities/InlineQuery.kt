package org.yanex.telegram.entities

/**
 * This object represents an incoming inline query. When the user sends an empty query, your bot could return some default or trending results.
 *
 * @property id Unique identifier for this query.
 * @property from Sender.
 * @property location Sender location, only for bots that request user location.
 * @property query Text of the query (up to 512 characters).
 * @property offset Offset of the results to be returned, can be controlled by the bot.
 */
data class InlineQuery(
        val id: String,
        val from: User,
        val location: Location?,
        val query: String,
        val offset: String)