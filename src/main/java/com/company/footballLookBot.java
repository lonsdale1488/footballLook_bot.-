package com.company;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendSticker;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.stickers.Sticker;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class footballLookBot   extends TelegramLongPollingBot {
    int marker =0;
private static final  String APII = "579033849:AAFCphyesGthnergvyX0VmYvjwhxGI66-xc";
private static final   String footballLookBot = "BigGuru";
    private String db = "mongodb://java3:monkey3@ds241121.mlab.com:41121/heroku_sbfcgdq8";
    @Override
    public void onUpdateReceived(Update update) {
        MongoClientURI mongoClientURI = new MongoClientURI(db);
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        MongoDatabase database = mongoClient.getDatabase(mongoClientURI.getDatabase());
        MongoCollection<Document> expecries = database.getCollection( "sergiyExpenses");

        String text = update.getMessage().getText();
        Long chaill = update.getMessage().getChatId();
        SendMessage sand = new SendMessage().setChatId(chaill);

        if (text.equals("/start")) {
            sand.setText("Enter expenses");
        } else {
            Document document = new Document();
            document.append(Integer.toString(update.getMessage().getMessageId()), text);
            expecries.insertOne(document);
            sand.setText("Added");
        }

        try {
            execute(sand);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        /**
        if (text.contains("привіт")) {

            sand.setText("Привіт");
            sand.setChatId(chaill);
        }
        else if (text.contains("find")) {
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
            sand.setText("Для того щоб почавсі пошук напишіть 'find' ");
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
         */
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
