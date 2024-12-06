package org.example;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class Button {
    public static ReplyKeyboardMarkup sendBtns(Long chatId) {

        KeyboardButton keyboardButton1 = new KeyboardButton();
        keyboardButton1.setText("Add new goal");

        KeyboardButton keyboardButton2 = new KeyboardButton();
        keyboardButton2.setText("Update goal");

        KeyboardButton keyboardButton4 = new KeyboardButton();
        keyboardButton4.setText("Remove goal");

        KeyboardButton keyboardButton5 = new KeyboardButton();
        keyboardButton5.setText("Show goalsList");

        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(keyboardButton1);
        keyboardRow.add(keyboardButton2);

        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add(keyboardButton4);
        keyboardRow2.add(keyboardButton5);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(keyboardRow);
        keyboardRows.add(keyboardRow2);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(keyboardRows);

        return replyKeyboardMarkup;
    }
}
