package org.yanex.telegram.entities

import com.google.gson.annotations.SerializedName as Name

/**
 * This object represents a phone contact.
 *
 * @property photoNumber Contact's phone number.
 * @property firstName Contact's first name.
 * @property lastName Contact's last name.
 * @property userId Contact's user identifier in Telegram.
 */
data class Contact(
        @Name("photo_number") val photoNumber: String,
        @Name("first_name") val firstName: String,
        @Name("last_name") val lastName: String?,
        @Name("user_id") val userId: Long?)