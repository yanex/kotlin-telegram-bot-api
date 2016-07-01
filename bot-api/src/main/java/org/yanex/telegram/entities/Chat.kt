package org.yanex.telegram.entities

import com.google.gson.annotations.SerializedName as Name

/**
 * This object represents a chat.
 *
 * @property id Unique identifier for this chat.
 * @property type Type of chat, can be either “private”, “group”, “supergroup” or “channel”.
 * @property title Title, for channels and group chats.
 * @property userName Username, for private chats, supergroups and channels if available.
 * @property firstName First name of the other party in a private chat.
 * @property lastName Last name of the other party in a private chat.
 */
data class Chat(
        val id: Long,
        val type: String,
        val title: String?,
        @Name("username") val userName: String?,
        @Name("first_name") val firstName: String?,
        @Name("last_name") val lastName: String?)