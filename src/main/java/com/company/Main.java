package com.company;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class Main {

    public static void main(String[] args) {

	ApiContextInitializer.init();
	TelegramBotsApi api = new TelegramBotsApi();
        try {
            api.registerBot ( new footballLookBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }



    }
}
