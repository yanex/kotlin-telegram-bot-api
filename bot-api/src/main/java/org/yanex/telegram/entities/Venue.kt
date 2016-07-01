package org.yanex.telegram.entities

import com.google.gson.annotations.SerializedName as Name

/**
 * This object represents a venue.

 * @param location Venue location.
 * @param title Name of the venue.
 * @param address Address of the venue.
 * @param foursquareId Foursquare identifier of the venue.
 */
data class Venue(
        val location: Location, 
        val title: String,
        val address: String,
        @Name("foursquare_id") val foursquareId: String?
)