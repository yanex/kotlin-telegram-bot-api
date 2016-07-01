package org.yanex.telegram.entities

/**
 * This object represents one special entity in a text message. For example, hashtags, usernames, URLs, etc.
 * 
 * @property type Type of the entity. 
 *           Can be mention (@username), hashtag, bot_command, url, email, bold (bold text), italic (italic text), 
 *           code (monowidth string), pre (monowidth block), text_link (for clickable text URLs), 
 *           text_mention (for users without usernames).
 * @property offset Offset in UTF-16 code units to the start of the entity.
 * @property length Length of the entity in UTF-16 code units.
 * @property url For “text_link” only, url that will be opened after user taps on the text.
 * @property user For “text_mention” only, the mentioned user.
 */
data class MessageEntity(
        val type: String,
        val offset: Int,
        val length: Int,
        val url: String?,
        val user: User?)