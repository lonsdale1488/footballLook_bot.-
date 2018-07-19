package com.company;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class footballLookBot extends TelegramLongPollingBot {
    int marker = 0;
    private static final String APII = "579033849:AAEJHJt1QHdfAkgXDn7ahHbJR5v-IbI3Jck";
    private static final String footballLookBot = "BigGuru";
    private String db = "mongodb://java3:monkey3@ds241121.mlab.com:41121/heroku_sbfcgdq8";

    @Override
    public void onUpdateReceived(Update update) {
//        MongoClientURI mongoClientURI = new MongoClientURI(db);
//        MongoClient mongoClient = new MongoClient(mongoClientURI);
//        MongoDatabase database = mongoClient.getDatabase(mongoClientURI.getDatabase());
//        MongoCollection<Document> expecries = database.getCollection( "sergiyExpenses");

//        String text = update.getMessage().getText();
//        Long chaill = update.getMessage().getChatId();


//        SendMessage sand = new SendMessage().setChatId(chaill);
        SendMessage sendMessage = new SendMessage();

        if (update.hasMessage()) {
            String test = update.getMessage().getText();
            sendMessage.setChatId(update.getMessage().getChatId());
            if (test.equals("/start")) {

                InlineKeyboardButton inlineKeyboardButtonYes = new InlineKeyboardButton();
                InlineKeyboardButton inlineKeyboardButtonNo = new InlineKeyboardButton();
                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();


                inlineKeyboardButtonYes.setText("Yes");
                inlineKeyboardButtonYes.setCallbackData("yes");

                inlineKeyboardButtonNo.setText("No");
                inlineKeyboardButtonNo.setCallbackData("no");

                List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();


                List<InlineKeyboardButton> row = new ArrayList<>();

                row.add(inlineKeyboardButtonYes);
                row.add(inlineKeyboardButtonNo);

                keyboard.add(row);

                inlineKeyboardMarkup.setKeyboard(keyboard);

                sendMessage.setText("Op");
                sendMessage.setReplyMarkup(inlineKeyboardMarkup);
            }
        } else if (update.hasCallbackQuery()) {

            String callbac = update.getCallbackQuery().getData();
            sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
            if (callbac.equals("yes"))
            {
                ReplyKeyboardMarkup replyKeyboardMarkup =new ReplyKeyboardMarkup();

                List<KeyboardRow> list = new ArrayList<>();
                KeyboardRow row = new KeyboardRow();
                row.add("ok \uD83D\uDC09");
                row.add("yes \uD83D\uDC0D");
                list.add(row);

                replyKeyboardMarkup.setKeyboard(list);
                sendMessage.setText("Op");
                sendMessage.setReplyMarkup(replyKeyboardMarkup);
            } else if (callbac.equals("no")) {
                sendMessage.setText("Ok. Try '/start' again later.");
            }
        }

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
//
//        if (text.equals("/start")) {
//            sand.setText("Enter expenses");
//        } else {
//            Document document = new Document();
//            document.append(Integer.toString(update.getMessage().getMessageId()), text);
//            expecries.insertOne(document);
//            sand.setText("Added");
//        }
//
//        try {
//            execute(sand);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }


//        if (text.contains("привіт")) {
//
//            sand.setText("Привіт");
//            sand.setChatId(chaill);
//        }
//        else if (text.contains("find")) {
//            sand.setText("Введи кого ти хочеш знайти англійською");
//            sand.setChatId(chaill);
//            marker = 1;
//        }
//        else  if (marker == 1)
//        {
//
//             LogictJson logictJson = new LogictJson();
//                String url =  logictJson.JSONAnswer(text);
//            sand.setText(url);
//            sand.setChatId(chaill);
//            marker = 0;
//        }
//        else {
//            sand.setText("Для того щоб почавсі пошук напишіть 'find' ");
//            sand.setChatId(chaill);}
//        System.out.print(marker);
//
//
//
//       // System.out.print(text.indexOf("Привіт"));
//
//
//
////            sand.setText(text);
////            sand.setChatId(chaill);
//            try {
//                execute(sand);
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//
//        }
//
    @Override
    public String getBotUsername() {
        return footballLookBot;
    }

    @Override
    public String getBotToken() {
        return APII;
    }
}
