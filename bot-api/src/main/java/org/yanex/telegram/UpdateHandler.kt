package org.yanex.telegram

interface UpdateHandler {
    fun handleError(response: Response<*>): Boolean = true
    fun handleUnknown(message: Message) {}
    fun handleCommand(message: Message, command: String, args: String) {}
    fun handleTextMessage(message: Message, text: String) {}
    fun handleAudioMessage(message: Message, audio: Audio) {}
    fun handlePhotoMessage(message: Message, photo: List<PhotoSize>, caption: String?) {}
    fun handleDocumentMessage(message: Message, document: Document) {}
    fun handleStickerMessage(message: Message, sticker: Sticker) {}
    fun handleVideoMessage(message: Message, video: Video, caption: String?) {}
    fun handleContactMessage(message: Message, contact: Contact) {}
    fun handleLocationMessage(message: Message, location: Location) {}

    fun handleMessage(message: Message) {
        if (message.text != null) {
            val matcher = COMMAND_REGEXP.matcher(message.text)
            if (matcher.matches()) {
                val command = matcher.group(1)
                val args = matcher.group(3)
                handleCommand(message, command, args)
            } else {
                handleTextMessage(message, message.text)
            }
        } else if (message.audio != null) {
            handleAudioMessage(message, message.audio)
        } else if (message.photo != null) {
            handlePhotoMessage(message, message.photo, message.caption)
        } else if (message.document != null) {
            handleDocumentMessage(message, message.document)
        } else if (message.sticker != null) {
            handleStickerMessage(message, message.sticker)
        } else if (message.video != null) {
            handleVideoMessage(message, message.video, message.caption)
        } else if (message.contact != null) {
            handleContactMessage(message, message.contact)
        } else if (message.location != null) {
            handleLocationMessage(message, message.location)
        } else {
            handleUnknown(message)
        }
    }
}

interface StrictUpdateHandler : UpdateHandler {
    override fun handleError(response: Response<*>) = throw NotImplementedError()
    override fun handleCommand(message: Message, command: String, args: String) = throw NotImplementedError()
    override fun handleTextMessage(message: Message, text: String) = throw NotImplementedError()
    override fun handleAudioMessage(message: Message, audio: Audio) = throw NotImplementedError()
    override fun handlePhotoMessage(message: Message, photo: List<PhotoSize>, caption: String?) = throw NotImplementedError()
    override fun handleDocumentMessage(message: Message, document: Document) = throw NotImplementedError()
    override fun handleStickerMessage(message: Message, sticker: Sticker) = throw NotImplementedError()
    override fun handleVideoMessage(message: Message, video: Video, caption: String?) = throw NotImplementedError()
    override fun handleContactMessage(message: Message, contact: Contact) = throw NotImplementedError()
    override fun handleLocationMessage(message: Message, location: Location) = throw NotImplementedError()
}

internal val COMMAND_REGEXP = "^\\/([A-Za-z0-9_]{1,32})( (.*))?$".toPattern()