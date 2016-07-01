package org.yanex.telegram.entities

import com.google.gson.annotations.SerializedName as Name

/**
 * This object represents a message.
 * 
 * @property messageId Unique message identifier.
 * @property from Sender, can be empty for messages sent to channels.
 * @property date Date the message was sent in Unix time.
 * @property chat Conversation the message belongs to.
 * @property forwardFrom For forwarded messages, sender of the original message.
 * @property forwardFromChat For messages forwarded from a channel, information about the original channel.
 * @property forwardDate For forwarded messages, date the original message was sent in Unix time.
 * @property replyToMessage For replies, the original message. 
 *           Note that the Message object in this field will not contain further reply_to_message fields even if it itself is a reply.
 * @property editDate the message was last edited in Unix time.
 * @property text For text messages, the actual UTF-8 text of the message, 0-4096 characters.
 * @property entities List of For text messages, special entities like usernames, URLs, bot commands, etc. that appear in the text.
 * @property audio Message is an audio file, information about the file.
 * @property document Message is a general file, information about the file.
 * @property photo List of Message is a photo, available sizes of the photo.
 * @property sticker Message is a sticker, information about the sticker.
 * @property video Message is a video, information about the video.
 * @property voice Message is a voice message, information about the file.
 * @property caption Caption for the document, photo or video, 0-200 characters.
 * @property contact Message is a shared contact, information about the contact.
 * @property location Message is a shared location, information about the location.
 * @property venue Message is a venue, information about the venue.
 * @property newChatMember A new member was added to the group, information about them (this member may be the bot itself).
 * @property leftChatMember A member was removed from the group, information about them (this member may be the bot itself).
 * @property newChatTitle A chat title was changed to this value.
 * @property newChatPhoto  List of A chat photo was change to this value.
 * @property deleteChatPhoto Service message: the chat photo was deleted.
 * @property groupChatCreated Service message: the group has been created.
 * @property supergroupChatCreated Service message: the supergroup has been created. 
 *           This field can‘t be received in a message coming through updates, because bot can’t be a member of a supergroup when it is created. 
 *           It can only be found in reply_to_message if someone replies to a very first message in a directly created supergroup.
 * @property channelChatCreated Service message: the channel has been created. 
 *           This field can‘t be received in a message coming through updates, because bot can’t be a member of a channel when it is created. 
 *           It can only be found in reply_to_message if someone replies to a very first message in a channel.
 * @property migrateToChatId The group has been migrated to a supergroup with the specified identifier.
 * @property migrateFromChatId The supergroup has been migrated from a group with the specified identifier.
 * @property pinnedMessage Specified message was pinned. 
 *           Note that the Message object in this field will not contain further reply_to_message fields even if it is itself a reply.
 */
data class Message(
        @Name("message_id") val messageId: Long,
        val from: User?,
        val date: Int,
        val chat: Chat,
        @Name("forward_from") val forwardFrom: User?,
        @Name("forward_from_chat") val forwardFromChat: Chat?,
        @Name("forward_date") val forwardDate: Int?,
        @Name("reply_to_message") val replyToMessage: Message?,
        @Name("edit_date") val editDate: Int?,
        val text: String?,
        val entities: List<MessageEntity>?,
        val audio: Audio?,
        val document: Document?,
        val photo: List<PhotoSize>?,
        val sticker: Sticker?,
        val video: Video?,
        val voice: Voice?,
        val caption: String?,
        val contact: Contact?,
        val location: Location?,
        val venue: Venue?,
        @Name("new_chat_member") val newChatMember: User?,
        @Name("left_chat_member") val leftChatMember: User?,
        @Name("new_chat_title") val newChatTitle: String?,
        @Name("new_chat_photo") val newChatPhoto: List<PhotoSize>?,
        @Name("delete_chat_photo") val deleteChatPhoto: Boolean,
        @Name("group_chat_created") val groupChatCreated: Boolean,
        @Name("supergroup_chat_created") val supergroupChatCreated: Boolean,
        @Name("channel_chat_created") val channelChatCreated: Boolean,
        @Name("migrate_to_chat_id") val migrateToChatId: Boolean,
        @Name("migrate_from_chat_id") val migrateFromChatId: Boolean,
        @Name("pinned_message") val pinnedMessage: Message)