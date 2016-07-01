package org.yanex.telegram.entities

import com.google.gson.annotations.SerializedName as Name

/**
 * This object represents an audio file to be treated as music by the Telegram clients.
 * 
 * @property fileId Unique identifier for this file.
 * @property duration Duration of the audio in seconds as defined by sender.
 * @property performer Performer of the audio as defined by sender or by audio tags.
 * @property title Title of the audio as defined by sender or by audio tags.
 * @property mimeType MIME type of the file as defined by sender.
 * @property fileSize File size.
 */
data class Audio(
        @Name("file_id") val fileId: String,
        val duration: Int,
        val performer: String?,
        val title: String?,
        @Name("mime_type") val mimeType: String?,
        @Name("file_size") val fileSize: Int?)