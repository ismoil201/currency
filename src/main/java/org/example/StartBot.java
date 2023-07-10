package org.example;

import org.example.service.CurrencyService;
import org.example.service.CurrencyServiceImpl;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class StartBot {
    public static void main(String[] args) {

//        CurrencyService currencyService  = new CurrencyServiceImpl();

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            CurrencyBot currencyBot = new CurrencyBot("6299794429:AAE8WDjxMx3uIoExAJXMRNaOVSbrt51l8Ec"
                    );
            telegramBotsApi.registerBot(currencyBot);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
