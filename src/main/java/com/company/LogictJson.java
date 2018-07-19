package com.company;

import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;

public class LogictJson {

    public String Response(int j) {
        disableSSL();
        String response = "";
        try { response = footballSaits(j);
            //   System.out.print(response);
            String fin = " ";
            if (response == null)
            {return null;}

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private static String footballSaits(int j ) throws Exception {

        String firstSait = "https://newsapi.org/v2/everything?sources=bbc-sport&apiKey=594c628349a7407c801b272d236001dd";
        String SecondSait = "https://newsapi.org/v2/top-headlines?sources=the-sport-bible&apiKey=594c628349a7407c801b272d236001dd";
        String threeSait = "https://newsapi.org/v2/everything?sources=talksport&apiKey=594c628349a7407c801b272d236001dd";
        String url;
        if (j == 0){url = firstSait;}
        else if (j == 1){url = SecondSait;}
        else { url = threeSait;}
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void disableSSL() {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String JSONAnswer(String name) {
        String answer = "Я нічого не знайшов";
        for (int j=0; j<3; j++){
        JSONObject obj = new JSONObject(Response(j));
        JSONArray array = obj.getJSONArray("articles");
        for (int i = 0; i < array.length(); i++) {
            // витягую і  шукаю
            JSONObject objc = array.getJSONObject(i);

            String description = objc.getString("description");
            String urlnews = objc.getString("url");
            System.out.print("\n" +description + "\n"+name);
            if (description.contains(name))
            {
               // description.contentEquals()
                answer = urlnews;
                break;
            }
        }

        }
        return answer;
    }

}
