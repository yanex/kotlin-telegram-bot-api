package org.yanex.telegram.entities

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName as Name

/**
 * Upon receiving a message with this object, Telegram clients will display a reply interface to the user 
 * (act as if the user has selected the bot‘s message and tapped ’Reply'). 
 * This can be extremely useful if you want to create user-friendly step-by-step interfaces without having to sacrifice privacy mode.

 * @property forceReply Shows reply interface to the user, as if they manually selected the bot‘s message and tapped ’Reply'.
 * @property selective Use this parameter if you want to force reply from specific users only. Targets:
 *           1) users that are @mentioned in the text of the Message object;
 *           2) if the bot's message is a reply (has reply_to_message_id), sender of the original message.
 */
data class ForceReplyMarkup(
        @Name("force_reply") val forceReply: Boolean = true,
        val selective: Boolean? = null
) : ReplyMarkup {
    private companion object {
        val GSON = Gson()
    }
    override fun toString(): String = GSON.toJson(this)
}