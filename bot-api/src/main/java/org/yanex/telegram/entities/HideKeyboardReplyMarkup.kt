package org.yanex.telegram.entities

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

/**
 * Upon receiving a message with this object, Telegram clients will hide the current custom keyboard and display the default letter-keyboard. 
 * By default, custom keyboards are displayed until a new keyboard is sent by a bot. 
 * An exception is made for one-time keyboards that are hidden immediately after the user presses a button.
 * 
 * Example: A user votes in a poll, bot returns confirmation message in reply to the vote and hides keyboard 
 * for that user, while still showing the keyboard with poll options to users who haven't voted yet.

 * @property hideKeyboard Requests clients to hide the custom keyboard.
 * @property selective Use this parameter if you want to hide keyboard for specific users only. Targets: 
 *           1) users that are @mentioned in the text of the Message object; 
 *           2) if the bot's message is a reply (has reply_to_message_id), sender of the original message.
 */
data class HideKeyboardReplyMarkup(
        @SerializedName("hide_keyboard") val hideKeyboard: Boolean = true,
        val selective: Boolean? = null
) : ReplyMarkup {
    private companion object {
        val GSON = Gson()
    }
    override fun toString(): String = GSON.toJson(this)
}