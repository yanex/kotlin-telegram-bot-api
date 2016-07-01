package org.yanex.telegram.entities

import com.google.gson.annotations.SerializedName

/**
 * Voice
This object represents a voice note.
 *
 * @property fileId Unique identifier for this file.
 * @property duration Duration of the audio in seconds as defined by sender.
 * @property mimeType MIME type of the file as defined by sender.
 * @property fileSize File size.
 */
data class Voice(
        @SerializedName("file_id") val fileId: String,
        val duration: Int,
        @SerializedName("mime_type") val mimeType: String?,
        @SerializedName("file_size") val fileSize: Int?)