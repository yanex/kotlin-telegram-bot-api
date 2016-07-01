package org.yanex.telegram.entities

import com.google.gson.annotations.SerializedName

/**
 * This object represents a Telegram user or bot.
 * 
 * @property id Unique identifier for this user or bot.
 * @property firstName User‘s or bot’s first name.
 * @property lastName User‘s or bot’s last name.
 * @property userName User‘s or bot’s username.
 */
data class User(
        val id: Long,
        @SerializedName("first_name") val firstName: String,
        @SerializedName("last_name") val lastName: String?,
        @SerializedName("username") val userName: String?)