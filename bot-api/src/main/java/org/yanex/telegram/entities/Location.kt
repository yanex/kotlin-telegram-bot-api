package org.yanex.telegram.entities

/**
 * This object represents a point on the map.
 *
 * @property longitude Longitude as defined by sender.
 * @property latitude Latitude as defined by sender.
 */
data class Location(
        val longitude: Float,
        val latitude: Float)