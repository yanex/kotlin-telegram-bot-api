package org.yanex.telegram.entities

import com.google.gson.annotations.SerializedName

/**
 * This object represent a user's profile pictures.
 *
 * @property totalCount Total number of profile pictures the target user has.
 * @property photos Requested profile pictures (in up to 4 sizes each).
 */
data class UserProfilePhotos(
        @SerializedName("total_count") val totalCount: Int,
        val photos: List<List<PhotoSize>>)