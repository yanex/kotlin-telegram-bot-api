package org.yanex.telegram.entities

import com.google.gson.annotations.SerializedName as Name

/**
 * This object represents a general file (as opposed to photos, voice messages and audio files).
 *
 * @property fileId Unique file identifier.
 * @property thumb Document thumbnail as defined by sender.
 * @property fileName Original filename as defined by sender.
 * @property mimeType MIME type of the file as defined by sender.
 * @property fileSize File size.
 */
data class Document(
        @Name("file_id") val fileId: String,
        val thumb: PhotoSize?,
        @Name("file_name") val fileName: String?,
        @Name("mime_type") val mimeType: String?,
        @Name("file_size") val fileSize: Int?)