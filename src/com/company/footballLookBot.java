package com.company;

import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendSticker;
import org.telegram.telegrambots.api.objects.File;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.Video;
import org.telegram.telegrambots.api.objects.stickers.Sticker;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class footballLookBot   extends TelegramLongPollingBot {
    int marker =0;
private static final  String APII = "579033849:AAFCphyesGthnergvyX0VmYvjwhxGI66-xc";
private static final   String footballLookBot = "BigGuru";
    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage()) ;

        String text = update.getMessage().getText();
        Long chaill = update.getMessage().getChatId();
        SendMessage sand = new SendMessage();

        //System.out.print(text);



        if (text.contains("привіт")) {

            sand.setText("Привіт");
            sand.setChatId(chaill);
        }
        else if (text.contains("знайди")) {
            sand.setText("Введи кого ти хочеш знайти англійською");
            sand.setChatId(chaill);
            marker = 1;
        }
        else  if (marker == 1)
        {

             LogictJson logictJson = new LogictJson();
                String url =  logictJson.JSONAnswer(text);
            sand.setText(url);
            sand.setChatId(chaill);
            marker = 0;
        }
        else {
            sand.setText("Для того щоб почавсі пошук напишіть 'знайди' ");
            sand.setChatId(chaill);}
        System.out.print(marker);



       // System.out.print(text.indexOf("Привіт"));



//            sand.setText(text);
//            sand.setChatId(chaill);
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
