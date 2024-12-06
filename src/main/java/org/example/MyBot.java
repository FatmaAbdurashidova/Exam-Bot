package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class MyBot extends TelegramLongPollingBot {
    private List<User> userList = new ArrayList<>();
    //    private List<Goal> goalList = new ArrayList<>();
    private UserService userService;
    private GoalService goalService;
    Goal goal = new Goal();

    public MyBot() {
        this.userService = new UserService(this);
        this.goalService = new GoalService(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            User user = userService
                    .getUserByChatId(chatId);
            switch (message) {
                case "/start" -> {
                    sendMessage(chatId,"Assalomu alaykum! Reja va Maqsadlar botiga xush kelibsiz");
                    break;
                }
                case "Add new goal" -> {
                    sendReplyButtons(chatId,Components.ADD_TASK);
                    user.setUserStep(UserStep.TASK);
                    break;

                }
                case "Update goal" -> {
                    sendReplyButtons(chatId,Components.UPDATE_TASK);
                    user.setUserStep(UserStep.UPDATE);
                    break;
                }
                case "Remove goal" -> {
                    sendReplyButtons(chatId,Components.DELETE_TASK);
                    user.setUserStep(UserStep.DELETE);
                    break;
                }
                case "Show goalsList"->{
                    String isList = goalService.viewAllGoals();
                    sendMessage(chatId, goalService.viewAllGoals());
                    break;
                }

            }switch (user.getUserStep()){
                case TASK -> {
                    boolean isCreated = goalService.addGoal(chatId, message);
                    sendMessage(chatId, Components.SUCCESS_ADDED_TASK);
                    user.setUserStep(UserStep.DONE);
                    break;
                } case UPDATE -> {
                    goalService.updateGoal(chatId, message);
                    sendMessage(chatId, Components.SUCCESS_UPDATED_TASK);
                    user.setUserStep(UserStep.DONE);
                    break;
                }
                case DELETE -> {
                    goalService.deleteGoal(chatId,message);
                    sendMessage(chatId, Components.SUCCESS_DELETED_TASK);
                    user.setUserStep(UserStep.DONE);
                    break;
                }
            }
        }
    }

//    private void start(long chatId) {
//        sendMessage(chatId, "Assalomu alaykum");
//    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        message.setReplyMarkup(Button.sendBtns(chatId));

        try {
            execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendReplyButtons(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        message.setReplyMarkup(Button.sendBtns(chatId));

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "t.me/somethinga23_bot";
    }

    @Override
    public String getBotToken() {
        return "7723298991:AAFQIHHDCiGb6SAgU76buX_ZPHQM6Rmd00Q";
    }
}
