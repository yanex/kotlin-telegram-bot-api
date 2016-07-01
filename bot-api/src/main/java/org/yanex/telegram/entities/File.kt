package org.yanex.telegram.entities

import com.google.gson.annotations.SerializedName as Name

/**
 * This object represents a file ready to be downloaded.
 * 
 * The file can be downloaded via the link https://api.telegram.org/file/bot<token>/<file_path>.
 * 
 * It is guaranteed that the link will be valid for at least 1 hour. 
 * When the link expires, a new one can be requested by calling getFile().

 * @property fileId Unique identifier for this file.
 * @property fileSize File size, if known.
 * @property filePath File path. Use https://api.telegram.org/file/bot<token>/<file_path> to get the file.
 */
data class File(
        @Name("file_id") val fileId: String,
        @Name("file_size") val fileSize: Int?,
        @Name("file_path") val filePath: String?)