package com.knubisoft.bot.user;

import java.util.HashMap;
import java.util.Map;

public class UserSessionService {

    private Map<Long, Integer> userSessionMap = new HashMap<>();


    public void saveUserSession(Long chatId, Integer percentage) {
        userSessionMap.put(chatId, percentage);
    }

    public void  removeUserSession(Long chatId) {
        userSessionMap.remove(chatId);
    }

    public Integer getPercentageIfUserSessionExists(Long chatId) {
        return userSessionMap.get(chatId);
    }

    public boolean isUserSessionExists(Long chatId) {
        return userSessionMap.containsKey(chatId);
    }
}
