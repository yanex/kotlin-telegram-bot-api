package org.yanex.telegram.example

import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.Properties

class TelegramProperties {
    val token: String
        get() = readProperties().getProperty(PROP_TOKEN) ?: throw RuntimeException("Token is not set in " + FILE_NAME)

    var lastId: Long
        get() = readProperties().getProperty(PROP_LAST_ID)?.let { it.toLong() } ?: 0
        set(lastId) = writeProperties(readProperties().apply {
            setProperty(PROP_LAST_ID, lastId.toString())
        })

    private fun writeProperties(props: Properties) {
        FileOutputStream(FILE_NAME).use { output -> props.store(output, null) }
    }

    private fun readProperties() = Properties().apply {
        FileInputStream(FILE_NAME).use { input -> load(input) }
    }

    private companion object {
        val FILE_NAME = "telegram.properties"

        val PROP_TOKEN = "token"
        val PROP_LAST_ID = "lastId"
    }
}
