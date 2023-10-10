package com.knubisoft.bot.util;

import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import static java.lang.String.format;

@UtilityClass
public class SendMessageUtil {

    public SendMessage buildStartCommandResponse(Long chatId) {
        return SendMessage.builder()
                .chatId(chatId.toString())
                .text("Hello, please provide percentage threshold for prices")
                .build();
    }

    public SendMessage buildStopCommandResponse(Long chatId) {
        return SendMessage.builder()
                .chatId(chatId.toString())
                .text("Goodbye, all the best to you!")
                .build();
    }

    public SendMessage buildInvalidPercentageResponse(Long chatId, String invalidInput) {
        return SendMessage.builder()
                .chatId(chatId.toString())
                .text(format("You have entered invalid percentage value [%s], please re-enter valid integer value",
                        invalidInput))
                .build();
    }

    public SendMessage buildValidPercentageResponse(Long chatId, String validInput) {
        return SendMessage.builder()
                .chatId(chatId.toString())
                .text(format("You have entered [%s]", validInput))
                .build();
    }
}
