package org.yanex.telegram.entities

import com.google.gson.annotations.SerializedName as Name

/**
 * This object represents one button of the reply keyboard. 
 * For simple text buttons String can be used instead of this object to specify text of the button. Optional fields are mutually exclusive.
 *
 * @property text If none of the optional fields are used, it will be sent to the bot as a message when the button is pressed.
 * @property requestContact If true, the user's phone number will be sent as a contact when the button is pressed. Available in private chats only.
 * @property requestLocation If true, the user's current location will be sent when the button is pressed. Available in private chats only.
 */
data class KeyboardButton(
        val text: String,
        @Name("request_contact") val requestContact: Boolean,
        @Name("request_location") val requestLocation: Boolean)