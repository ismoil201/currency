package org.example.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.models.Currency;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class CurrencyServiceImpl implements CurrencyService {
    @Override
    public List<Currency> getCurrency() {
        FileService fileService = new FileServiceImpl();

        try {
            URL url = new URL("https://cbu.uz/uz/arkhiv-kursov-valyut/json/");
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String row;
            StringBuilder stringBuilder = new StringBuilder();
            while ((row = bufferedReader.readLine()) != null) {
                stringBuilder.append(row);
            }

            fileService.fileWrite(stringBuilder.toString() + " \n");
            String text = stringBuilder.toString();


            Type typeToken = new TypeToken<List<Currency>>() {
            }.getType();
            Gson gson = new Gson();
            List<Currency> currency = gson.fromJson(text, typeToken);



//            for (Currency currency1 :currency) {
//                System.out.println(currency1.getCcyNm_UZ());
//
//                String getInfo = "Pul birligi\uD83D\uDCB1 --> " + currency.getCcyNm_UZ() + "\n" +
//                        currency.getNominal() + "\uD83D\uDCB8 "
//                        + currency.getCcyNm_UZ() + " --> "
//                        + currency.getRate() + " so'm\uD83D\uDCB8 \n" + "⌚\uFE0FYangilangan vaqti --> " +
//                        currency.getDate();
//
//
//            }
//            String getInfo = "\uD83C\uDFDB" + currency.get(0).getCcy();


//            System.out.println(getInfo);
            return currency;
        } catch (Exception e) {
            e.printStackTrace();
        }
    return null;
    }
}
