package org.yanex.telegram.entities

/**
 * This object contains information about one member of the chat.

 * @property user Information about the user.
 * @property status The member's status in the chat. Can be “creator”, “administrator”, “member”, “left” or “kicked”.
 */
data class ChatMember(
        val user: User,
        val status: String)