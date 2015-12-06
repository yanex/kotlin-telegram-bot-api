package org.yanex.telegram

import retrofit.RestAdapter
import retrofit.http.*
import retrofit.mime.TypedFile
import java.io.File
import java.nio.file.Files
import com.google.gson.annotations.SerializedName as Name

data class Response<T>(
        val result: T,
        val ok: Boolean,
        @Name("error_code") val errorCode: Int?,
        @Name("description") val errorDescription: String?)

class InputFile(
        file: File,
        mimeType: String? = null
) : TypedFile(mimeType ?: Files.probeContentType(file.toPath()), file)

interface TelegramBotService {
    @GET("/getMe")
    fun getMe(): Response<User>

    @POST("/sendMessage") @FormUrlEncoded
    fun sendMessage(
            @Field("chat_id") chatId: String,
            @Field("text") text: String,
            @Field("parse_mode") parseMode: String? = null,
            @Field("disable_web_page_preview") disableWebPagePreview: Boolean? = null,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: Reply? = null
    ): Response<Message>

    @POST("/forwardMessage") @FormUrlEncoded
    fun forwardMessage(
            @Field("chat_id") chatId: String,
            @Field("from_chat_id") fromChatId: String,
            @Field("message_id") messageId: Long
    ): Response<Message>

    @POST("/sendPhoto") @FormUrlEncoded
    fun sendPhoto(
            @Field("chat_id") chatId: String,
            @Field("photo") fileId: String,
            @Field("caption") caption: String? = null,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: Reply? = null
    ): Response<Message>

    @POST("/sendPhoto") @Multipart
    fun sendPhoto(
            @Part("chat_id") chatId: String,
            @Part("photo") photo: InputFile,
            @Part("caption") caption: String? = null,
            @Part("reply_to_message_id") replyToMessageId: Int? = null,
            @Part("reply_markup") replyMarkup: Reply? = null
    ): Response<Message>

    @POST("/sendAudio") @FormUrlEncoded
    fun sendAudio(
            @Field("chat_id") chatId: String,
            @Field("audio") fileId: String,
            @Field("duration") duration: Int? = null,
            @Field("performer") performer: String? = null,
            @Field("title") title: String? = null,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: Reply? = null
    ): Response<Message>

    @POST("/sendAudio") @Multipart
    fun sendAudio(
            @Part("chat_id") chatId: String,
            @Part("audio") audio: InputFile,
            @Part("duration") duration: Int? = null,
            @Part("performer") performer: String? = null,
            @Part("title") title: String? = null,
            @Part("reply_to_message_id") replyToMessageId: Int? = null,
            @Part("reply_markup") replyMarkup: Reply? = null
    ): Response<Message>

    @POST("/sendDocument") @FormUrlEncoded
    fun sendDocument(
            @Field("chat_id") chatId: String,
            @Field("document") fileId: String,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: Reply? = null
    ): Response<Message>

    @POST("/sendDocument") @Multipart
    fun sendDocument(
            @Part("chat_id") chatId: String,
            @Part("document") document: InputFile,
            @Part("reply_to_message_id") replyToMessageId: Int? = null,
            @Part("reply_markup") replyMarkup: Reply? = null
    ): Response<Message>

    @POST("/sendSticker") @FormUrlEncoded
    fun sendSticker(
            @Field("chat_id") chatId: String,
            @Field("sticker") fileId: String,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: Reply? = null
    ): Response<Message>

    @POST("/sendSticker") @Multipart
    fun sendSticker(
            @Part("chat_id") chatId: String,
            @Part("sticker") sticker: InputFile,
            @Part("reply_to_message_id") replyToMessageId: Int? = null,
            @Part("reply_markup") replyMarkup: Reply? = null
    ): Response<Message>

    @POST("/sendVideo") @FormUrlEncoded
    fun sendVideo(
            @Field("chat_id") chatId: String,
            @Field("video") fileId: String,
            @Field("duration") duration: Int? = null,
            @Field("caption") caption: String? = null,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: Reply? = null
    ): Response<Message>

    @POST("/sendVideo") @Multipart
    fun sendVideo(
            @Part("chat_id") chatId: String,
            @Part("video") video: InputFile,
            @Part("duration") duration: Int? = null,
            @Part("caption") caption: String? = null,
            @Part("reply_to_message_id") replyToMessageId: Int? = null,
            @Part("reply_markup") replyMarkup: Reply? = null
    ): Response<Message>

    @POST("/sendVoice") @FormUrlEncoded
    fun sendVoice(
            @Field("chat_id") chatId: String,
            @Field("voice") fileId: String,
            @Field("duration") duration: Int? = null,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: Reply? = null
    ): Response<Message>

    @POST("/sendVoice") @Multipart
    fun sendVoice(
            @Part("chat_id") chatId: String,
            @Part("voice") voice: InputFile,
            @Part("duration") duration: Int? = null,
            @Part("reply_to_message_id") replyToMessageId: Int? = null,
            @Part("reply_markup") replyMarkup: Reply? = null
    ): Response<Message>

    @POST("/sendLocation") @FormUrlEncoded
    fun sendLocation(
            @Field("chat_id") chatId: String,
            @Field("latitude") latitude: Float,
            @Field("longitude") longitude: Float,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: Reply? = null
    ): Response<Message>

    @POST("/sendChatAction") @FormUrlEncoded
    fun sendChatAction(
            @Field("chat_id") chatId: String,
            @Field("action") action: ChatAction
    ): Response<Boolean>

    @GET("/getUserProfilePhotos")
    fun getUserProfilePhotos(
            @Path("user_id") userId: Long,
            @Path("offset") offset: Int? = null,
            @Path("limit") limit: Int? = null
    ): Response<UserProfilePhotos>
}