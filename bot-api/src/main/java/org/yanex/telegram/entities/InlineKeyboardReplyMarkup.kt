package org.yanex.telegram.entities

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName as Name

/**
 * This object represents an inline keyboard that appears right next to the message it belongs to.
 *
 * @property inlineKeyboard List of button rows, each represented by an List of InlineKeyboardButton objects.
 */
data class InlineKeyboardReplyMarkup(
        @Name("inline_keyboard") val inlineKeyboard: List<List<InlineKeyboardButton>>) : ReplyMarkup {
    private companion object {
        val GSON = Gson()
    }
    override fun toString(): String = GSON.toJson(this)
}