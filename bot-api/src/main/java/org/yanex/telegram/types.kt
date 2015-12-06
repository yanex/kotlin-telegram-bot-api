package org.yanex.telegram

import com.google.gson.annotations.SerializedName as Name

data class User(
        val id: Long,
        @Name("first_name") val firstName: String,
        @Name("last_name") val lastName: String?,
        @Name("username") val userName: String?)

data class Chat(
        val id: Long,
        val type: String,
        val title: String?,
        @Name("username") val userName: String?,
        @Name("first_name") val firstName: String?,
        @Name("last_name") val lastName: String?)

data class Message(
        @Name("message_id") val messageId: Long,
        val from: User?,
        val date: Int,
        val chat: Chat,
        @Name("forward_from") val forwardFrom: User?,
        @Name("forward_date") val forwardDate: Int?,
        @Name("reply_to_message") val replyToMessage: Message?,
        val text: String?,
        val audio: Audio?,
        val document: Document?,
        val photo: List<PhotoSize>?,
        val sticker: Sticker?,
        val video: Video?,
        val voice: Voice?,
        val caption: String?,
        val contact: Contact?,
        val location: Location?,
        @Name("new_chat_participant") val newChatParticipant: User?,
        @Name("left_chat_participant") val leftChatParticipant: User?,
        @Name("new_chat_title") val newChatTitle: String?,
        @Name("new_chat_photo") val newChatPhoto: List<PhotoSize>?,
        @Name("delete_chat_photo") val deleteChatPhoto: Boolean,
        @Name("group_chat_created") val groupChatCreated: Boolean,
        @Name("supergroup_chat_created") val supergroupChatCreated: Boolean,
        @Name("channel_chat_created") val channelChatCreated: Boolean,
        @Name("migrate_to_chat_id") val migrateToChatId: Boolean,
        @Name("migrate_from_chat_id") val migrateFromChatId: Boolean)

data class PhotoSize(
        @Name("file_id") val fileId: String,
        val width: Int,
        val height: Int,
        @Name("file_size") val fileSize: Int?)

data class Audio(
        @Name("file_id") val fileId: String,
        val duration: Int,
        val performer: String?,
        val title: String?,
        @Name("mime_type") val mimeType: String?,
        @Name("file_size") val fileSize: Int?)

data class Document(
        @Name("file_id") val fileId: String,
        val thumb: PhotoSize?,
        @Name("file_name") val fileName: String?,
        @Name("mime_type") val mimeType: String?,
        @Name("file_size") val fileSize: Int?)

data class Sticker(
        @Name("file_id") val fileId: String,
        val width: Int,
        val height: Int,
        val thumb: PhotoSize?,
        @Name("file_size") val fileSize: Int?)

data class Video(
        @Name("file_id") val fileId: String,
        val width: Int,
        val height: Int,
        val duration: Int,
        val thumb: PhotoSize?,
        @Name("mime_type") val mimeType: String?,
        @Name("file_size") val fileSize: Int?)

data class Voice(
        @Name("file_id") val fileId: String,
        val duration: Int,
        @Name("mime_type") val mimeType: String?,
        @Name("file_size") val fileSize: Int?)

data class Contact(
        @Name("photo_number") val photoNumber: String,
        @Name("first_name") val firstName: String,
        @Name("last_name") val lastName: String?,
        @Name("user_id") val userId: Long?)

data class Location(
        val longitude: Float,
        val latitude: Float)

data class UserProfilePhotos(
        @Name("total_count") val totalCount: Int,
        val photos: List<List<PhotoSize>>)

data class File(
        @Name("file_id") val fileId: String,
        @Name("file_size") val fileSize: Int?,
        @Name("file_path") val filePath: String?)

interface Reply {
    val selective: Boolean
}

data class ReplyKeyboardMarkup(
        val keyboard: List<List<String>>,
        @Name("resize_keyboard") val resizeKeyboard: Boolean,
        @Name("one_time_keyboard") val oneTimeKeyboard: Boolean,
        override val selective: Boolean) : Reply

data class ReplyKeyboardHide(
        @Name("hide_keyboard") val hideKeyboard: Boolean,
        override val selective: Boolean) : Reply

data class ForceReply(
        @Name("force_reply") val forceReply: Boolean,
        override val selective: Boolean): Reply

data class Update(
        @Name("update_id") val updateId: Long,
        val message: Message)

enum class ChatAction(val apiName: String) {
    TYPING("typing"),
    UPLOAD_PHOTO("upload_photo"),
    RECORD_VIDEO("record_video"),
    UPLOAD_VIDEO("upload_video"),
    RECORD_AUDIO("record_audio"),
    UPLOAD_AUDIO("upload_audio"),
    UPLOAD_DOCUMENT("upload_document"),
    FIND_LOCATION("find_location")
}