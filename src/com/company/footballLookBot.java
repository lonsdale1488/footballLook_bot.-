package com.company;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class footballLookBot   extends TelegramLongPollingBot {

private static final  String APII = "579033849:AAFCphyesGthnergvyX0VmYvjwhxGI66-xc";
private static final   String footballLookBot = "Hi people";
    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage());
        String text = update.getMessage().getText();
        Long chaill = update.getMessage().getChatId();
        SendMessage sand = new SendMessage();
        sand.setText(text);
        sand.setChatId(chaill);
        try {
            execute(sand);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return footballLookBot;
    }

    @Override
    public String getBotToken() {
        return APII;
    }
}
