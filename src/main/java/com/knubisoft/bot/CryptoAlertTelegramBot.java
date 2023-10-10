package com.knubisoft.bot;

import com.knubisoft.bot.config.TelegramConfig;
import com.knubisoft.bot.user.UserSessionService;
import com.knubisoft.bot.util.SendMessageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
@RequiredArgsConstructor
public class CryptoAlertTelegramBot extends TelegramLongPollingBot {

    private final TelegramConfig telegramConfig;
    private final UserSessionService userSessionService;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String textFromUser = update.getMessage().getText();

            Long chatId = update.getMessage().getChatId();

            SendMessage sendMessage;

            switch (textFromUser) {
                case "/start":
                    sendMessage = SendMessageUtil.buildStartCommandResponse(chatId);
                    break;
                case "/stop":
                    sendMessage = SendMessageUtil.buildStopCommandResponse(chatId);
                    userSessionService.removeUserSession(chatId);
                    break;
                case "/refresh":
                    //re-enter new percentage and refresh algo
                default:
                    try {
                        Integer percentage = Integer.parseInt(textFromUser);
                        if (userSessionService.isUserSessionExists(chatId)) {
                            sendMessage = //notify that user has already entered percentage
                        } else {
                            userSessionService.saveUserSession(chatId, percentage);
                            sendMessage = SendMessageUtil.buildValidPercentageResponse(chatId, textFromUser);
                        }
                    } catch (NumberFormatException e) {
                        sendMessage = SendMessageUtil.buildInvalidPercentageResponse(chatId, textFromUser);
                    }
            }

            try {
                this.sendApiMethod(sendMessage);
            } catch (TelegramApiException e) {
                log.error("Exception when sending message: ", e);
            }
        } else {
            log.warn("Unexpected update from user");
        }
    }

    @Override
    public String getBotUsername() {
        return telegramConfig.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return telegramConfig.getToken();
    }
}
