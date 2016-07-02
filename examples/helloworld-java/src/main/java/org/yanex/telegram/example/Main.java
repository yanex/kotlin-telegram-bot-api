package org.yanex.telegram.example;

import org.jetbrains.annotations.NotNull;
import org.yanex.telegram.TelegramBot;
import org.yanex.telegram.entities.Message;
import org.yanex.telegram.entities.Update;
import org.yanex.telegram.handler.AbstractUpdateVisitor;
import org.yanex.telegram.handler.StopProcessingException;
import org.yanex.telegram.handler.VisitorUpdateHandler;
import retrofit.RestAdapter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        TelegramProperties properties = new TelegramProperties(); 
        final TelegramBot bot = TelegramBot.create(properties.getToken(), RestAdapter.LogLevel.FULL);
        long lastId = bot.listen(properties.getLastId(), new VisitorUpdateHandler(new AbstractUpdateVisitor() {
            @Override
            public boolean visitText(@NotNull Update update, @NotNull Message message, @NotNull String text) {
                if (text.equals("ping")) {
                    sendText(update, "pong");
                    return true;
                } else if (text.equals("exit")) {
                    throw new StopProcessingException();
                }
                return false;
            }

            @Override
            public void visitUpdate(@NotNull Update update) {
                sendText(update, "Unknown command. Try 'ping'.");
            }
            
            private void sendText(Update update, String text) {
                bot.sendMessage(update.getSenderId(), text, null, null, null, null, null);
            }
        }));
        properties.setLastId(lastId);
    }
}
