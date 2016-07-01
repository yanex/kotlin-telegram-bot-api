package org.yanex.telegram.entities

import com.google.gson.annotations.SerializedName

/**
 * This object represents a video file.
 *
 * @property fileId Unique identifier for this file.
 * @property width Video width as defined by sender.
 * @property height Video height as defined by sender.
 * @property duration Duration of the video in seconds as defined by sender.
 * @property thumb Video thumbnail.
 * @property mimeType Mime type of a file as defined by sender.
 * @property fileSize File size.
 */
data class Video(
        @SerializedName("file_id") val fileId: String,
        val width: Int,
        val height: Int,
        val duration: Int,
        val thumb: PhotoSize?,
        @SerializedName("mime_type") val mimeType: String?,
        @SerializedName("file_size") val fileSize: Int?)