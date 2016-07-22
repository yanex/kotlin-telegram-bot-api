package org.yanex.telegram

import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody
import org.yanex.telegram.entities.*
import retrofit2.Call
import retrofit2.http.*
import java.nio.file.Files
import com.google.gson.annotations.SerializedName as Name
import java.io.File as IoFile

private val PLAIN_TEXT_MIME = MediaType.parse("text/plain")
private val APPLICATION_JSON_MIME = MediaType.parse("application/json")

data class Response<T>(
        val result: T?,
        val ok: Boolean,
        @Name("error_code") val errorCode: Int?,
        @Name("description") val errorDescription: String?)

private fun inputFile(file: IoFile, mimeType: String? = null): RequestBody {
    return RequestBody.create(MediaType.parse(mimeType ?: Files.probeContentType(file.toPath())), file)
}

//private val GSON = Gson()

private fun requestString(text: String) = RequestBody.create(PLAIN_TEXT_MIME, text)

private fun requestJson(text: String) = RequestBody.create(APPLICATION_JSON_MIME, text) 

interface TelegramBotService {
    @GET("getMe")
    fun getMe(): Call<Response<User>>

    @POST("sendMessage") @FormUrlEncoded
    fun sendMessage(
            @Field("chat_id") chatId: String,
            @Field("text") text: String,
            @Field("parse_mode") parseMode: String? = null,
            @Field("disable_web_page_preview") disableWebPagePreview: Boolean? = null,
            @Field("disable_notification") disableNotification: Boolean? = null,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("sendMessage") @FormUrlEncoded
    fun sendMessage(
            @Field("chat_id") chatId: Long,
            @Field("text") text: String,
            @Field("parse_mode") parseMode: String? = null,
            @Field("disable_web_page_preview") disableWebPagePreview: Boolean? = null,
            @Field("disable_notification") disableNotification: Boolean? = null,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("forwardMessage") @FormUrlEncoded
    fun forwardMessage(
            @Field("chat_id") chatId: String,
            @Field("from_chat_id") fromChatId: String,
            @Field("message_id") messageId: Long,
            @Field("disable_notification") disableNotification: Boolean? = null
    ): Call<Response<Message>>

    @POST("forwardMessage") @FormUrlEncoded
    fun forwardMessage(
            @Field("chat_id") chatId: Long,
            @Field("from_chat_id") fromChatId: String,
            @Field("message_id") messageId: Long,
            @Field("disable_notification") disableNotification: Boolean? = null
    ): Call<Response<Message>>

    @POST("sendPhoto") @FormUrlEncoded
    fun sendPhoto(
            @Field("chat_id") chatId: String,
            @Field("photo") fileId: String,
            @Field("caption") caption: String? = null,
            @Field("disable_notification") disableNotification: Boolean? = null,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("sendPhoto") @FormUrlEncoded
    fun sendPhoto(
            @Field("chat_id") chatId: Long,
            @Field("photo") fileId: String,
            @Field("caption") caption: String? = null,
            @Field("disable_notification") disableNotification: Boolean? = null,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("sendPhoto") @Multipart
    fun sendPhoto(
            @Part("chat_id") chatId: RequestBody,
            @Part("photo") photo: RequestBody,
            @Part("caption") caption: RequestBody?,
            @Part("disable_notification") disableNotification: RequestBody?,
            @Part("reply_to_message_id") replyToMessageId: RequestBody?,
            @Part("reply_markup") replyMarkup: RequestBody?
    ): Call<Response<Message>>

    fun sendPhoto(
            chatId: String,
            photo: IoFile,
            caption: String? = null,
            disableNotification: Boolean? = null,
            replyToMessageId: Int? = null,
            replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>> {
        return sendPhoto(
                requestString(chatId),
                inputFile(photo),
                if (caption != null) requestString(caption) else null,
                if (disableNotification != null) requestString(disableNotification.toString()) else null,
                if (replyToMessageId != null) requestString(replyToMessageId.toString()) else null,
                if (replyMarkup != null) requestJson(replyMarkup.toString()) else null
        )
    }
    
    fun sendPhoto(
            chatId: Long,
            photo: IoFile,
            caption: String? = null,
            disableNotification: Boolean? = null,
            replyToMessageId: Int? = null,
            replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>> {
        return sendPhoto(
                requestString(chatId.toString()),
                inputFile(photo),
                if (caption != null) requestString(caption) else null,
                if (disableNotification != null) requestString(disableNotification.toString()) else null,
                if (replyToMessageId != null) requestString(replyToMessageId.toString()) else null,
                if (replyMarkup != null) requestJson(replyMarkup.toString()) else null
        )
    }

    @POST("sendAudio") @FormUrlEncoded
    fun sendAudio(
            @Field("chat_id") chatId: String,
            @Field("audio") fileId: String,
            @Field("duration") duration: Int? = null,
            @Field("performer") performer: String? = null,
            @Field("title") title: String? = null,
            @Field("disable_notification") disableNotification: Boolean? = null,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("sendAudio") @FormUrlEncoded
    fun sendAudio(
            @Field("chat_id") chatId: Long,
            @Field("audio") fileId: String,
            @Field("duration") duration: Int? = null,
            @Field("performer") performer: String? = null,
            @Field("title") title: String? = null,
            @Field("disable_notification") disableNotification: Boolean? = null,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("sendAudio") @Multipart
    fun sendAudio(
            @Part("chat_id") chatId: String,
            @Part("audio") audio: RequestBody,
            @Part("duration") duration: Int? = null,
            @Part("performer") performer: String? = null,
            @Part("title") title: String? = null,
            @Part("disable_notification") disableNotification: Boolean? = null,
            @Part("reply_to_message_id") replyToMessageId: Int? = null,
            @Part("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("sendAudio") @Multipart
    fun sendAudio(
            @Part("chat_id") chatId: Long,
            @Part("audio") audio: RequestBody,
            @Part("duration") duration: Int? = null,
            @Part("performer") performer: String? = null,
            @Part("title") title: String? = null,
            @Part("disable_notification") disableNotification: Boolean? = null,
            @Part("reply_to_message_id") replyToMessageId: Int? = null,
            @Part("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("sendDocument") @FormUrlEncoded
    fun sendDocument(
            @Field("chat_id") chatId: String,
            @Field("document") fileId: String,
            @Field("caption") caption: String? = null,
            @Field("disable_notification") disableNotification: Boolean? = null,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("sendDocument") @FormUrlEncoded
    fun sendDocument(
            @Field("chat_id") chatId: Long,
            @Field("document") fileId: String,
            @Field("caption") caption: String? = null,
            @Field("disable_notification") disableNotification: Boolean? = null,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("sendDocument") @Multipart
    fun sendDocument(
            @Part("chat_id") chatId: String,
            @Part("document") document: RequestBody,
            @Part("caption") caption: String? = null,
            @Part("disable_notification") disableNotification: Boolean? = null,
            @Part("reply_to_message_id") replyToMessageId: Int? = null,
            @Part("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("sendDocument") @Multipart
    fun sendDocument(
            @Part("chat_id") chatId: Long,
            @Part("document") document: RequestBody,
            @Part("caption") caption: String? = null,
            @Part("disable_notification") disableNotification: Boolean? = null,
            @Part("reply_to_message_id") replyToMessageId: Int? = null,
            @Part("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("sendSticker") @FormUrlEncoded
    fun sendSticker(
            @Field("chat_id") chatId: String,
            @Field("sticker") fileId: String,
            @Field("disable_notification") disableNotification: Boolean? = null,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("sendSticker") @FormUrlEncoded
    fun sendSticker(
            @Field("chat_id") chatId: Long,
            @Field("sticker") fileId: String,
            @Field("disable_notification") disableNotification: Boolean? = null,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("sendSticker") @Multipart
    fun sendSticker(
            @Part("chat_id") chatId: String,
            @Part("sticker") sticker: RequestBody,
            @Part("disable_notification") disableNotification: Boolean? = null,
            @Part("reply_to_message_id") replyToMessageId: Int? = null,
            @Part("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("sendSticker") @Multipart
    fun sendSticker(
            @Part("chat_id") chatId: Long,
            @Part("sticker") sticker: RequestBody,
            @Part("disable_notification") disableNotification: Boolean? = null,
            @Part("reply_to_message_id") replyToMessageId: Int? = null,
            @Part("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("sendVideo") @FormUrlEncoded
    fun sendVideo(
            @Field("chat_id") chatId: String,
            @Field("video") fileId: String,
            @Field("duration") duration: Int? = null,
            @Field("width") width: Int? = null,
            @Field("height") height: Int? = null,
            @Field("caption") caption: String? = null,
            @Field("disable_notification") disableNotification: Boolean? = null,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("sendVideo") @FormUrlEncoded
    fun sendVideo(
            @Field("chat_id") chatId: Long,
            @Field("video") fileId: String,
            @Field("duration") duration: Int? = null,
            @Field("width") width: Int? = null,
            @Field("height") height: Int? = null,
            @Field("caption") caption: String? = null,
            @Field("disable_notification") disableNotification: Boolean? = null,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("sendVideo") @Multipart
    fun sendVideo(
            @Part("chat_id") chatId: String,
            @Part("video") video: RequestBody,
            @Part("duration") duration: Int? = null,
            @Part("width") width: Int? = null,
            @Part("height") height: Int? = null,
            @Part("caption") caption: String? = null,
            @Part("disable_notification") disableNotification: Boolean? = null,
            @Part("reply_to_message_id") replyToMessageId: Int? = null,
            @Part("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("sendVideo") @Multipart
    fun sendVideo(
            @Part("chat_id") chatId: Long,
            @Part("video") video: RequestBody,
            @Part("duration") duration: Int? = null,
            @Part("width") width: Int? = null,
            @Part("height") height: Int? = null,
            @Part("caption") caption: String? = null,
            @Part("disable_notification") disableNotification: Boolean? = null,
            @Part("reply_to_message_id") replyToMessageId: Int? = null,
            @Part("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("sendVoice") @FormUrlEncoded
    fun sendVoice(
            @Field("chat_id") chatId: String,
            @Field("voice") fileId: String,
            @Field("duration") duration: Int? = null,
            @Field("disable_notification") disableNotification: Boolean? = null,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("sendVoice") @FormUrlEncoded
    fun sendVoice(
            @Field("chat_id") chatId: Long,
            @Field("voice") fileId: String,
            @Field("duration") duration: Int? = null,
            @Field("disable_notification") disableNotification: Boolean? = null,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("sendVoice") @Multipart
    fun sendVoice(
            @Part("chat_id") chatId: String,
            @Part("voice") voice: RequestBody,
            @Part("duration") duration: Int? = null,
            @Part("disable_notification") disableNotification: Boolean? = null,
            @Part("reply_to_message_id") replyToMessageId: Int? = null,
            @Part("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("sendVoice") @Multipart
    fun sendVoice(
            @Part("chat_id") chatId: Long,
            @Part("voice") voice: RequestBody,
            @Part("duration") duration: Int? = null,
            @Part("disable_notification") disableNotification: Boolean? = null,
            @Part("reply_to_message_id") replyToMessageId: Int? = null,
            @Part("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("sendLocation") @FormUrlEncoded
    fun sendLocation(
            @Field("chat_id") chatId: String,
            @Field("latitude") latitude: Float,
            @Field("longitude") longitude: Float,
            @Field("disable_notification") disableNotification: Boolean? = null,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("sendLocation") @FormUrlEncoded
    fun sendLocation(
            @Field("chat_id") chatId: Long,
            @Field("latitude") latitude: Float,
            @Field("longitude") longitude: Float,
            @Field("disable_notification") disableNotification: Boolean? = null,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("sendVenue") @FormUrlEncoded
    fun sendVenue(
            @Field("chat_id") chatId: String,
            @Field("latitude") latitude: Float,
            @Field("longitude") longitude: Float,
            @Field("title") title: String,
            @Field("address") address: String,
            @Field("foursquare_id") foursquareId: String?,
            @Field("disable_notification") disableNotification: Boolean? = null,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("sendVenue") @FormUrlEncoded
    fun sendVenue(
            @Field("chat_id") chatId: Long,
            @Field("latitude") latitude: Float,
            @Field("longitude") longitude: Float,
            @Field("title") title: String,
            @Field("address") address: String,
            @Field("foursquare_id") foursquareId: String?,
            @Field("disable_notification") disableNotification: Boolean? = null,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("sendContact") @FormUrlEncoded
    fun sendContact(
            @Field("chat_id") chatId: String,
            @Field("phone_number") phoneNumber: String,
            @Field("first_name") firstName: String,
            @Field("last_name") lastName: String?,
            @Field("disable_notification") disableNotification: Boolean? = null,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("sendContact") @FormUrlEncoded
    fun sendContact(
            @Field("chat_id") chatId: Long,
            @Field("phone_number") phoneNumber: String,
            @Field("first_name") firstName: String,
            @Field("last_name") lastName: String?,
            @Field("disable_notification") disableNotification: Boolean? = null,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Call<Response<Message>>

    @POST("sendChatAction") @FormUrlEncoded
    fun sendChatAction(
            @Field("chat_id") chatId: String,
            @Field("action") action: ChatAction
    ): Call<Response<Boolean>>

    @POST("sendChatAction") @FormUrlEncoded
    fun sendChatAction(
            @Field("chat_id") chatId: Long,
            @Field("action") action: ChatAction
    ): Call<Response<Boolean>>

    @GET("getUserProfilePhotos")
    fun getUserProfilePhotos(
            @Query("user_id") userId: Long,
            @Query("offset") offset: Int? = null,
            @Query("limit") limit: Int? = null
    ): Call<Response<UserProfilePhotos>>

    @GET("getFile")
    fun getUserProfilePhotos(
            @Query("file_id") fileId: String
    ): Call<Response<File>>

    @POST("kickChatMember") @FormUrlEncoded
    fun kickChatMember(
            @Field("chat_id") chatId: String,
            @Field("user_id") userId: Long
    ): Call<Response<Boolean>>

    @POST("leaveChat") @FormUrlEncoded
    fun leaveChat(
            @Field("chat_id") chatId: String
    ): Call<Response<Boolean>>

    @POST("unbanChatMember") @FormUrlEncoded
    fun unbanChatMember(
            @Field("chat_id") chatId: String,
            @Field("user_id") userId: Long
    ): Call<Response<Boolean>>

    @GET("getChat")
    fun getChat(
            @Query("chat_id") chatId: String
    ): Call<Response<Chat>>

    @GET("getChatAdministrators")
    fun getChatAdministrators(
            @Query("chat_id") chatId: String
    ): Call<Response<List<ChatMember>>>

    @GET("getChatMembersCount")
    fun getChatMembersCount(
            @Query("chat_id") chatId: String
    ): Call<Response<Int>>

    @GET("getChatMember")
    fun getChatMember(
            @Query("chat_id") chatId: String
    ): Call<Response<ChatMember>>

    @POST("answerCallbackQuery") @FormUrlEncoded
    fun answerCallbackQuery(
            @Field("callback_query_id") callbackQueryId: String,
            @Field("text") text: String? = null,
            @Field("show_alert") showAlert: Boolean? = null
    ): Call<Response<Boolean>>
    
    /**
     * Use this method to receive incoming updates using long polling (wiki).
     * 
     * @param offset Identifier of the first update to be returned. 
     *        Must be greater by one than the highest among the identifiers of previously received updates. 
     *        By default, updates starting with the earliest unconfirmed update are returned. 
     *        An update is considered confirmed as soon as getUpdates is called with an offset higher than its update_id.
     *        The negative offset can be specified to retrieve updates starting from -offset update from the end of the updates queue.
     *        All previous updates will be forgotten.
     * @param limit
     * @param timeout
     * 
     * @return an array of [Update] objects.
     */
    @GET("getUpdates")
    fun getUpdates(
            @Query("offset") offset: Long? = null,
            @Query("limit") limit: Int? = null,
            @Query("timeout") timeout: Int? = null
    ): Call<Response<List<Update>>>

    /**
     * Use this method to specify a url and receive incoming updates via an outgoing webhook.
     * 
     * @param url HTTPS url to send updates to. Use an empty string to remove webhook integration.
     * @param certificate Upload your public key certificate so that the root certificate in use can be checked.
     */
    @POST("setWebhook") @Multipart
    fun setWebhook(
            @Part("url") url: RequestBody,
            @Part("certificate") certificate: RequestBody
    ): Call<Response<Unit>>
    
    fun setWebhook(url: String, certificate: IoFile): Call<Response<Unit>> {
        return setWebhook(requestString(url), inputFile(certificate))
    }

    @POST("setWebhook")
    fun removeWebhook()
}