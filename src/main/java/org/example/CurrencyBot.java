package org.example;

import org.example.service.CurrencyService;
import org.example.service.CurrencyServiceImpl;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class CurrencyBot extends TelegramLongPollingBot {


   CurrencyService currencyServicee= new CurrencyServiceImpl();

    public CurrencyBot(String botToken) {
        super(botToken);

    }


    @Override
    public void onUpdateReceived(Update update) {

        if(update.hasMessage()){
            String text = update.getMessage().getText();
            String userName = update.getMessage().getChat().getFirstName();

            SendMessage sendMessage = new SendMessage();

            Long chatID = update.getMessage().getChatId();

            sendMessage.setChatId(chatID);

            printTerminal(userName,text);

            switch (text){
                case "/start" -> {
                    menuStart(sendMessage, userName);
                }case "Uzbek\uD83C\uDDFA\uD83C\uDDFF" ->{
                    uzbekMenu(sendMessage);
                }case "\uD83D\uDCDD Bot haqida"->{
                    sendMessage.setText("Bu bot Bank\uD83C\uDFDB serveriga ulangan bo'lib har kuni 24/7 yangilanadi" +
                            " va  har qanday vaqtda malumotlarni  bilishingiz mumkin ✅");
                }case "\uD83D\uDD19Orqaga" ->{
                    menuStart(sendMessage,userName);
                }case "\uD83D\uDCB8 Kurs haqida bilish"->{
                    kursMenuUz(sendMessage);
                }case "US-DOL\uD83C\uDDFA\uD83C\uDDF8\uD83D\uDCB0\n" +
                        "UZ-SO'M\uD83C\uDDFA\uD83C\uDDFF\uD83D\uDD04"->{
                    kursAqshUz(currencyServicee,sendMessage);
                }case "RUS-RUBL\uD83C\uDDF7\uD83C\uDDFA\uD83D\uDCB8\n" +
                        "UZ-SO'M\uD83C\uDDFA\uD83C\uDDFF\uD83D\uDD04"->{
                    kursRublUz(currencyServicee,sendMessage);
                }case "KOR-WON\uD83C\uDDF0\uD83C\uDDF7\uD83D\uDCB8\n" +
                        "UZ-SO'M\uD83C\uDDFA\uD83C\uDDFF\uD83D\uDD04" ->{
                    kursWonlUz(currencyServicee,sendMessage);
                }case "Kankulyator\uD83D\uDD04"->{
                    conculateUs(update,currencyServicee,sendMessage);
                }
                //--------       -------------      ---------------
                case "Русский\uD83C\uDDF7\uD83C\uDDFA" ->{
                    rusMenu(sendMessage);
                }case "\uD83D\uDCDD О боте"->{
                    sendMessage.setText("Этот бот подключен к серверу банка \uD83C\uDFDB и обновляется каждый день " +
                            "24/7 и вы можете узнать данные в любое время ✅");
                }case "\uD83D\uDCB8 Узнать о курсе"->{
                    kursMenuRu(sendMessage);
                }case "\uD83D\uDD19Назад"->{
                    menuStart(sendMessage,userName);
                }case "US-DOL\uD83C\uDDFA\uD83C\uDDF8\uD83D\uDCB0\n" +
                        "УЗ-СУМ\uD83C\uDDFA\uD83C\uDDFF\uD83D\uDD04"-> {
                    kursAqshRu(currencyServicee, sendMessage);
                }case "RUS-RUBL\uD83C\uDDF7\uD83C\uDDFA\uD83D\uDCB8\n" +
                        "УЗ-СУМ\uD83C\uDDFA\uD83C\uDDFF\uD83D\uDD04"->{
                    kursRublRu(currencyServicee,sendMessage);
                }case"KOR-WON\uD83C\uDDF0\uD83C\uDDF7\uD83D\uDCB8\n" +
                        "УЗ-СУМ\uD83C\uDDFA\uD83C\uDDFF\uD83D\uDD04"->{
                    kursWonRu(currencyServicee,sendMessage);

                }default -> {
                    sendMessage.setText("XATO KIRITILDI, BOTNI\uD83E\uDD16 QAYTA YURUTISH \uD83D\uDC49/start");
                }


            }


            executeMassage(sendMessage);

        }

    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @Override
    public String getBotUsername() {
        return "valyuta2023_bot";
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }
    private void executeMassage(SendMessage sendMessage){
        try {
            execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private  void printTerminal(String userName, String text){
        System.out.println(userName +"-->"+text );
    }

    private  void menuStart(SendMessage sendMessage, String userName){
        sendMessage.setText("Assallomu aleykum\uD83D\uDC4B "+userName);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> buttons = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();

        KeyboardButton uzbek = new KeyboardButton();
        uzbek.setText("Uzbek\uD83C\uDDFA\uD83C\uDDFF");

        KeyboardButton rus = new KeyboardButton();
        rus.setText("Русский\uD83C\uDDF7\uD83C\uDDFA");

        keyboardRow.add(uzbek);
        keyboardRow.add(rus);

        buttons.add(keyboardRow);

        replyKeyboardMarkup.setKeyboard(buttons);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }
    private void uzbekMenu(SendMessage sendMessage){
        sendMessage.setText("\uD83C\uDF10Valyuta hisoblagich botiga Xush kelibsiz. Bot yordamida valyuta " +
                "narxlarini bilishingiz mumkin♻\uFE0F\uD83D\uDCB8");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> button = new ArrayList<>();
        KeyboardRow keyboardRow  = new KeyboardRow();

        KeyboardButton kursInfo = new KeyboardButton();
        kursInfo.setText("\uD83D\uDCB8 Kurs haqida bilish");

        KeyboardButton botInfo = new KeyboardButton();
        botInfo.setText("\uD83D\uDCDD Bot haqida");

        KeyboardButton orqaga = new KeyboardButton();
        orqaga.setText("\uD83D\uDD19Orqaga");

//        KeyboardButton canculate  =new KeyboardButton();
//        canculate.setText("Kankulyator\uD83D\uDD04");

        keyboardRow.add(kursInfo);
//        keyboardRow.add(canculate);
        keyboardRow.add(botInfo);
        keyboardRow.add(orqaga);

        button.add(keyboardRow);
        replyKeyboardMarkup.setKeyboard(button);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

    }
    private void conculateUs(Update u, CurrencyService c ,SendMessage sendMessage){
        sendMessage.setText("BU \uD83C\uDDFA\uD83C\uDDF8AQSH DOLLOR MIQDORINI KIRITING UZ SO'M-dagi " +
                "qiymatni hisoblab beradi\uD83D\uDD04 \nQiymat kiriting \uD83D\uDCB0");
        int num = Integer.parseInt(u.getMessage().getText());
        int normUS = Integer.parseInt(c.getCurrency().get(0).getRate());
        int totalUz = num*normUS;
        sendMessage.setText(num+"USD \uD83C\uDDFA\uD83C\uDDF8 -> "+totalUz+" SO'M\uD83C\uDDFA\uD83C\uDDFF");

    }
    private  void kursAqshUz(CurrencyService currencyService,SendMessage sendMessage) {
        try {

            sendMessage.setText("\uD83C\uDFDB" + currencyService.getCurrency().get(0).getCcy() + "\n" +
                    "Pul birligi\uD83D\uDCB2 --> " + currencyService.getCurrency().get(0).getCcyNm_UZ() + "" +
                    "\uD83C\uDDFA\uD83C\uDDF8 \n" +
                    currencyService.getCurrency().get(0).getNominal() + "\uD83D\uDCB8 "
                    + currencyService.getCurrency().get(0).getCcyNm_UZ() + " --> "
                    + currencyService.getCurrency().get(0).getRate() + " so'm\uD83D\uDCB8 \n" + "⌚\uFE0FYangilangan vaqti --> " +
                    currencyService.getCurrency().get(0).getDate());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private  void kursRublUz(CurrencyService currencyService,SendMessage sendMessage) {
        try {

            sendMessage.setText("\uD83C\uDFDB" + currencyService.getCurrency().get(2).getCcy() + "\n" +
                    "Pul birligi --> " + currencyService.getCurrency().get(2).getCcyNm_UZ() + "" +
                    "\uD83C\uDDF7\uD83C\uDDFA \n" +
                    currencyService.getCurrency().get(2).getNominal() + "\uD83D\uDCB8 "
                    + currencyService.getCurrency().get(2).getCcyNm_UZ() + " --> "
                    + currencyService.getCurrency().get(2).getRate() + " so'm\uD83D\uDCB8 \n" + "⌚\uFE0FYangilangan vaqti --> " +
                    currencyService.getCurrency().get(2).getDate());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private  void kursWonlUz(CurrencyService currencyService,SendMessage sendMessage) {
        try {

            sendMessage.setText("\uD83C\uDFDB" + currencyService.getCurrency().get(35).getCcy() + "\n" +
                    "Pul birligi - " + currencyService.getCurrency().get(35).getCcyNm_UZ() + "" +
                    "\uD83C\uDDF0\uD83C\uDDF7 \n" +
                    currencyService.getCurrency().get(35).getNominal() + "\uD83D\uDCB8 "
                    + currencyService.getCurrency().get(35).getCcyNm_UZ() + " --> "
                    + currencyService.getCurrency().get(35).getRate() + " so'm\uD83D\uDCB8 \n" + "⌚\uFE0FYangilangan vaqti --> " +
                    currencyService.getCurrency().get(35).getDate());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private  void kursMenuUz(SendMessage sendMessage){
        sendMessage.setText("ILTIMOS KUTING\uD83D\uDD04");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> button = new ArrayList<>();
        KeyboardRow keyboardRow  = new KeyboardRow();

        KeyboardButton dollor = new KeyboardButton();
        dollor.setText("US-DOL\uD83C\uDDFA\uD83C\uDDF8\uD83D\uDCB0\n" +
                "UZ-SO'M\uD83C\uDDFA\uD83C\uDDFF\uD83D\uDD04");

        KeyboardButton won = new KeyboardButton();
        won.setText("KOR-WON\uD83C\uDDF0\uD83C\uDDF7\uD83D\uDCB8\n" +
                "UZ-SO'M\uD83C\uDDFA\uD83C\uDDFF\uD83D\uDD04");


        KeyboardButton rubl = new KeyboardButton();
        rubl.setText("RUS-RUBL\uD83C\uDDF7\uD83C\uDDFA\uD83D\uDCB8\n" +
                "UZ-SO'M\uD83C\uDDFA\uD83C\uDDFF\uD83D\uDD04");

        KeyboardButton orqaga = new KeyboardButton();
        orqaga.setText("\uD83D\uDD19Orqaga");

        keyboardRow.add(dollor);
        keyboardRow.add(rubl);
        keyboardRow.add(won);
        keyboardRow.add(orqaga);


        button.add(keyboardRow);
        replyKeyboardMarkup.setKeyboard(button);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }


//------------           ---------------              ------------------

    private void rusMenu(SendMessage sendMessage){
        sendMessage.setText("\uD83C\uDF10Добро пожаловать в бот-калькулятор валют. " +
                "Быстро узнать цены на валюту можно с помощью бота♻\uFE0F\uD83D\uDCB8");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> button = new ArrayList<>();
        KeyboardRow keyboardRow  = new KeyboardRow();

        KeyboardButton kursInfo = new KeyboardButton();
        kursInfo.setText("\uD83D\uDCB8 Узнать о курсе");

        KeyboardButton botInfo = new KeyboardButton();
        botInfo.setText("\uD83D\uDCDD О боте");

        KeyboardButton orqaga = new KeyboardButton();
        orqaga.setText("\uD83D\uDD19Назад");

        keyboardRow.add(kursInfo);
        keyboardRow.add(botInfo);
        keyboardRow.add(orqaga);

        button.add(keyboardRow);
        replyKeyboardMarkup.setKeyboard(button);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }
    private  void kursAqshRu(CurrencyService currencyService,SendMessage sendMessage) {
        try {

            sendMessage.setText("\uD83C\uDFDB" + currencyService.getCurrency().get(0).getCcy() + "\n" +
                    "Денежная единица\uD83D\uDCB2 --> " + currencyService.getCurrency().get(0).getCcyNm_RU() + "" +
                    "\uD83C\uDDFA\uD83C\uDDF8 \n" +
                    currencyService.getCurrency().get(0).getNominal() + "\uD83D\uDCB8 "
                    + currencyService.getCurrency().get(0).getCcyNm_RU() + " --> "
                    + currencyService.getCurrency().get(0).getRate() + " сум \uD83D\uDCB8 \n" + "⌚\uFE0FОбновленное время --> " +
                    currencyService.getCurrency().get(0).getDate());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private  void kursWonRu(CurrencyService currencyService,SendMessage sendMessage) {
        try {

            sendMessage.setText("\uD83C\uDFDB" + currencyService.getCurrency().get(35).getCcy() + "\n" +
                    "Денежная единица\uD83D\uDCB2 --> " + currencyService.getCurrency().get(35).getCcyNm_RU() +
                    "\uD83C\uDDF0\uD83C\uDDF7 \n" +
                    currencyService.getCurrency().get(35).getNominal() + "\uD83D\uDCB8 "
                    + currencyService.getCurrency().get(35).getCcyNm_RU() + " --> "
                    + currencyService.getCurrency().get(35).getRate() + " сум \uD83D\uDCB8 \n" + "⌚\uFE0FОбновленное время --> " +
                    currencyService.getCurrency().get(35).getDate());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private  void kursRublRu(CurrencyService currencyService,SendMessage sendMessage) {
        try {

            sendMessage.setText("\uD83C\uDFDB" + currencyService.getCurrency().get(2).getCcy() + "\n" +
                    "Денежная единица\uD83D\uDCB2 --> " + currencyService.getCurrency().get(2).getCcyNm_RU() +
                    "\uD83C\uDDF7\uD83C\uDDFA \n" +
                    currencyService.getCurrency().get(2).getNominal() + "\uD83D\uDCB8 "
                    + currencyService.getCurrency().get(2).getCcyNm_RU() + " --> "
                    + currencyService.getCurrency().get(2).getRate() + " сум \uD83D\uDCB8 \n" + "⌚\uFE0FОбновленное время --> " +
                    currencyService.getCurrency().get(2).getDate());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private  void kursMenuRu(SendMessage sendMessage){
        sendMessage.setText("ПОЖАЛУЙСТА ПОДОЖДИТЕ\uD83D\uDD04");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> button = new ArrayList<>();
        KeyboardRow keyboardRow  = new KeyboardRow();

        KeyboardButton dollor = new KeyboardButton();
        dollor.setText("US-DOL\uD83C\uDDFA\uD83C\uDDF8\uD83D\uDCB0\n" +
                "УЗ-СУМ\uD83C\uDDFA\uD83C\uDDFF\uD83D\uDD04");

        KeyboardButton won = new KeyboardButton();
        won.setText("KOR-WON\uD83C\uDDF0\uD83C\uDDF7\uD83D\uDCB8\n" +
                "УЗ-СУМ\uD83C\uDDFA\uD83C\uDDFF\uD83D\uDD04");

        KeyboardButton rubl = new KeyboardButton();
        rubl.setText("RUS-RUBL\uD83C\uDDF7\uD83C\uDDFA\uD83D\uDCB8\n" +
                "УЗ-СУМ\uD83C\uDDFA\uD83C\uDDFF\uD83D\uDD04");

        KeyboardButton orqaga = new KeyboardButton();
        orqaga.setText("\uD83D\uDD19Назад");

        keyboardRow.add(dollor);
        keyboardRow.add(rubl);
        keyboardRow.add(won);
        keyboardRow.add(orqaga);

        button.add(keyboardRow);
        replyKeyboardMarkup.setKeyboard(button);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }







//        🏛Ccy  - CUP

//        "Pul birligi 💱"->CcyNm_UZ - Kuba pesosi📍

//        Nominal "1💸"->CcyNm_UZ - Kuba pesosi  -> Rate  - 482.09,->so'm💸
//
//        ⌚️Yangilangan vaqti ->Date- >10.07.2023"🔄

}


//