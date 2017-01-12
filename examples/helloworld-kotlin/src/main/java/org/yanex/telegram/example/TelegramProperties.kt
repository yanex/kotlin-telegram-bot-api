package org.yanex.telegram.example

import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.util.Properties

class TelegramProperties(val fileName: String = "telegram.properties") {
    val token: String
        get() = readProperties().getProperty(PROP_TOKEN) ?: throw RuntimeException("Token is not set in " + fileName)

    var lastId: Long
        get() = readProperties().getProperty(PROP_LAST_ID)?.let { it.toLong() } ?: 0
        set(lastId) = writeProperties(readProperties().apply {
            setProperty(PROP_LAST_ID, lastId.toString())
        })

    private fun writeProperties(props: Properties) {
        FileOutputStream(fileName).use { output -> props.store(output, null) }
    }

    private fun readProperties() = Properties().apply {
        try {
            FileInputStream(fileName).use { input -> load(input) }
        } catch (e: FileNotFoundException) {
            throw FileNotFoundException("File ${System.getProperty("user.dir")}${File.separator}$fileName not found.")
        }
    }

    companion object {
        val PROP_TOKEN = "token"
        val PROP_LAST_ID = "lastId"
    }
}
