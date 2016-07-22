package org.yanex.telegram.entities

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

/**
 * This object represents a custom keyboard with reply options.
 * 
 * Example: A user requests to change the bot‘s language, bot replies to the request with a keyboard to select the new language. 
 * Other users in the group don’t see the keyboard.
 *
 * @property keyboard List of button rows, each represented by an List of KeyboardButton objects
 * @property resizeKeyboard Requests clients to resize the keyboard vertically for optimal fit 
 *           (e.g., make the keyboard smaller if there are just two rows of buttons). 
 *           Defaults to false, in which case the custom keyboard is always of the same height as the app's standard keyboard.
 * @property oneTimeKeyboard Requests clients to hide the keyboard as soon as it's been used. 
 *           The keyboard will still be available, but clients will automatically display the usual 
 *           letter-keyboard in the chat – the user can press a special button in the input field
 *           to see the custom keyboard again. Defaults to false.
 * @property selective Use this parameter if you want to show the keyboard to specific users only. Targets: 
 *           1) users that are @mentioned in the text of the Message object; 
 *           2) if the bot's message is a reply (has reply_to_message_id), sender of the original message.
 */
data class KeyboardReplyMarkup(
        val keyboard: List<List<String>>,
        @SerializedName("resize_keyboard") val resizeKeyboard: Boolean = false,
        @SerializedName("one_time_keyboard") val oneTimeKeyboard: Boolean = false,
        val selective: Boolean? = null
) : ReplyMarkup {
    constructor(
            vararg keyboard: String, 
            resizeKeyboard: Boolean = false, 
            oneTimeKeyboard: Boolean = false,
            selective: Boolean? = null
    ) : this(listOf(keyboard.toList()), resizeKeyboard, oneTimeKeyboard, selective)

    private companion object {
        val GSON = Gson()
    }
    
    override fun toString(): String = GSON.toJson(this)
}