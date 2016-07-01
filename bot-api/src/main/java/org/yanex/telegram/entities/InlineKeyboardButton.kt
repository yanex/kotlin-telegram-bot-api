package org.yanex.telegram.entities

import com.google.gson.annotations.SerializedName as Name

/**
 * This object represents one button of an inline keyboard. You must use exactly one of the optional fields.
 * 
 * Note: This offers an easy way for users to start using your bot in inline mode when they are currently in a private chat with it.
 * Especially useful when combined with switch_pm… actions – in this case the user will be automatically returned 
 * to the chat they switched from, skipping the chat selection screen.
 *
 * @property text Label text on the button
 * @property url HTTP url to be opened when button is pressed
 * @property callbackData Data to be sent in a callback query to the bot when button is pressed, 1-64 bytes
 * @property switchInlineQuery If set, pressing the button will prompt the user to select one of their chats,
 *           open that chat and insert the bot‘s username and the specified inline query in the input field. 
 *           Can be empty, in which case just the bot’s username will be inserted.
 */
data class InlineKeyboardButton(
        val text: String, 
        val url: String?,
        @Name("callback_data") val callbackData: String?,
        @Name("switch_inline_query") val switchInlineQuery: String?)