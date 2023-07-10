package org.example.service;

import org.example.models.Currency;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

public interface CurrencyService {
    List<Currency> getCurrency();
}
